package io.github.ilikeyourhat.fmnw.convention

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType


val Project.androidBaseExtension: BaseExtension
    get() = extensions.getByType<BaseExtension>()