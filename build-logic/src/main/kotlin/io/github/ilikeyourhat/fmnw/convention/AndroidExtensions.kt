package io.github.ilikeyourhat.fmnw.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.getByType

internal fun PluginManager.requireAndroidGradlePlugin() {
    findPlugin("com.android.base") ?: throwNoAndroidPluginAppliedException()
}

internal fun Project.isAndroidApplication(): Boolean {
    return plugins.hasPlugin("com.android.application")
}

internal fun Project.isAndroidLibrary(): Boolean {
    return plugins.hasPlugin("com.android.library")
}

internal val Project.androidApplication: ApplicationExtension
    get() = extensions.getByType()

internal val Project.androidLibrary: LibraryExtension
    get() = extensions.getByType()

internal val Project.androidCommon: CommonExtension<*, *, *, *, *, *>
    get() = when {
        isAndroidApplication() -> androidApplication
        isAndroidLibrary() -> androidLibrary
        else -> throwNoAndroidPluginAppliedException()
    }

private fun throwNoAndroidPluginAppliedException(): Nothing {
    throw GradleException(
        "This plugin requires either com.android.application or com.android.library plugin applied"
    )
}
