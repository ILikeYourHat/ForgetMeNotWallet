package io.github.ilikeyourhat.fmnw.convention

import androidx.room.gradle.RoomGradlePlugin
import com.google.devtools.ksp.gradle.KspGradleSubplugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class RoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                requireAndroidGradlePlugin()
                apply(KspGradleSubplugin::class)
                apply(RoomGradlePlugin::class)
            }
            with(ksp) {
                arg("room.incremental", "true")
                arg("room.generateKotlin", "true")
            }
            with(room) {
                schemaDirectory("$projectDir/schemas")
            }
            with(both(dependencies, versionCatalog)) {
                ksp("room.compiler")
                implementation("room.runtime")
                implementation("room.ktx")
                testImplementation("room.testing")
            }
        }
    }
}
