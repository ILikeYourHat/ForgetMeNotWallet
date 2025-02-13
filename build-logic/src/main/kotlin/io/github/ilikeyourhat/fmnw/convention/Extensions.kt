package io.github.ilikeyourhat.fmnw.convention

import androidx.room.gradle.RoomExtension
import com.android.build.gradle.BaseExtension
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension

internal fun PluginManager.requireAndroidGradlePlugin() {
    findPlugin("com.android.base") ?: throw GradleException(
        "This plugin requires either com.android.application or com.android.library plugin applied"
    )
}

internal fun Project.isAndroidApplication(): Boolean {
    return plugins.hasPlugin("com.android.application")
}

internal val Project.androidCommon: BaseExtension
    get() = extensions.getByType()

internal val Project.ksp: KspExtension
    get() = extensions.getByType()

internal val Project.room: RoomExtension
    get() = extensions.getByType()

internal val Project.kotlin: KotlinAndroidExtension
    get() = extensions.getByType()

internal val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>()
        .named("libs")

internal fun <A, B> both(firstThing: A, secondThing: B) = firstThing to secondThing

internal fun Pair<DependencyHandler, VersionCatalog>.implementation(dependency: String) {
    first.add("implementation", second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    first.add("implementation", dependency)
}

internal fun Pair<DependencyHandler, VersionCatalog>.debugImplementation(dependency: String) {
    first.add("debugImplementation", second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.testImplementation(dependency: String) {
    first.add("testImplementation", second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.androidTestImplementation(dependency: String) {
    first.add("androidTestImplementation", second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.androidTestImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    first.add("androidTestImplementation", dependency)
}

internal fun Pair<DependencyHandler, VersionCatalog>.platform(
    dependency: String
): Provider<MinimalExternalModuleDependency> {
    return first.platform(second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.ksp(dependency: String) {
    first.add("ksp", second.findLibrary(dependency).get())
}

internal fun Pair<DependencyHandler, VersionCatalog>.lintChecks(dependency: String) {
    first.add("lintChecks", second.findLibrary(dependency).get())
}
