package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

class JvmConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
            }
            with(androidCommon) {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }
            with(kotlin) {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
            with(both(dependencies, versionCatalog)) {
                coreLibraryDesugaring("desugaring")
            }
        }
    }
}
