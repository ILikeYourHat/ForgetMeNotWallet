package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import pl.allegro.tech.build.axion.release.ReleasePlugin
import pl.allegro.tech.build.axion.release.domain.VersionConfig

class VersioningConventionPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply(ReleasePlugin::class)
            }
            if (isAndroidApplication()) {
                with(androidApplication) {
                    defaultConfig {
                        val version = extensions.getByType<VersionConfig>().version
                        versionCode = version.toVersionCode()
                        versionName = version
                    }
                }
            }
        }
    }

    @Suppress("MagicNumber")
    private fun String.toVersionCode(): Int {
        val semVerRegex = """(\d+)\.(\d{1,2})\.(\d{1,2})(\D.*)?""".toRegex()
        val groups = semVerRegex.matchEntire(this)?.destructured?.toList()
            ?: throw GradleException("Version must be in SemVer format, but was $this")
        val (major, minor, patch) = groups.take(3).map { it.toInt() }
        return major * 10000 + minor * 100 + patch
    }
}
