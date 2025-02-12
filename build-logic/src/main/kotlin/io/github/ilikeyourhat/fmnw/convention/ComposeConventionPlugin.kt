package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
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
        }
    }
}
