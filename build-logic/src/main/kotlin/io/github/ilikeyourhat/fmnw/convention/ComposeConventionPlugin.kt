package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
                apply(ComposeCompilerGradleSubplugin::class)
            }
            with(androidCommon) {
                buildFeatures.compose = true
            }
            with(kotlin) {
                compilerOptions {
                    optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
                }
            }
            with(both(dependencies, versionCatalog)) {
                lintChecks("compose.lint.checks")
                implementation(platform("compose.bom"))
                implementation("androidx.compose.material3")
                implementation("androidx.compose.ui.tooling.preview")
                debugImplementation("androidx.compose.ui.tooling")
                if (isAndroidApplication()) {
                    androidTestImplementation(platform("compose.bom"))
                    androidTestImplementation("androidx-compose-ui-test")
                }
            }
        }
    }
}
