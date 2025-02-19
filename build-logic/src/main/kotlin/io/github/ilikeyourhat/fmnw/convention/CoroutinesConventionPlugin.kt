package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.Plugin
import org.gradle.api.Project

class CoroutinesConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            if (isAndroidApplication()) {
                with(androidApplication) {
                    packaging {
                        resources.excludes += "DebugProbesKt.bin"
                    }
                }
            }
            with(both(dependencies, versionCatalog)) {
                implementation("kotlinx.coroutines.android")
                testImplementation("kotlinx.coroutines.test")
                testImplementation("turbine")
            }
        }
    }
}
