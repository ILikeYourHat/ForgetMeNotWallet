package io.github.ilikeyourhat.fmnw.convention
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import tech.apter.robolectric.junit.jupiter.gradle.plugin.RobolectricJUnitJupiterGradlePlugin

class RobolectricConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
                apply(RobolectricJUnitJupiterGradlePlugin::class)
            }
            with(both(dependencies, versionCatalog)) {
                testImplementation("robolectric")
            }
        }
    }
}
