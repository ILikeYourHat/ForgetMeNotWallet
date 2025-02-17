package io.github.ilikeyourhat.fmnw.convention

import io.gitlab.arturbosch.detekt.DetektPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class DetektConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(DetektPlugin::class)
            }
            with(detekt) {
                buildUponDefaultConfig = true
                config.setFrom("$rootDir/config/detekt.yml")
            }
            with(both(dependencies, versionCatalog)) {
                detektPlugins("detekt.formatting")
            }
        }
    }
}
