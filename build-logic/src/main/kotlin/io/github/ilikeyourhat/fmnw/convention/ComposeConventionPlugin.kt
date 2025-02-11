package io.github.ilikeyourhat.fmnw.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradleSubplugin
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(ComposeCompilerGradleSubplugin::class)
            }
            with(androidBaseExtension) {
                buildFeatures.compose = true
            }
            with(extensions.getByType<KotlinAndroidExtension>()) {
                compilerOptions {
                    optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
                }
            }
        }
    }
}
