package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class CodeStyleConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
                apply(DetektConventionPlugin::class)
            }
            with(androidCommon) {
                lint {
                    warningsAsErrors = true
                    lintConfig = file("$rootDir/config/lint.xml")
                }
            }
            with(kotlin) {
                compilerOptions {
                    allWarningsAsErrors.set(true)
                }
            }
        }
    }
}
