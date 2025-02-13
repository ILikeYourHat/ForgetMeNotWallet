package io.github.ilikeyourhat.fmnw.convention

import com.google.devtools.ksp.gradle.KspGradleSubplugin
import dagger.hilt.android.plugin.HiltGradlePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class HiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
                apply(KspGradleSubplugin::class)
                apply(HiltGradlePlugin::class)
            }
            with(both(dependencies, versionCatalog)) {
                ksp("hilt.compiler")
                implementation("hilt")
            }
        }
    }
}
