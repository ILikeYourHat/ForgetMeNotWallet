plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

kotlin {
    compilerOptions {
        allWarningsAsErrors = true
    }
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("../config/detekt.yml")
}

gradlePlugin {
    plugins {
        register("codeStyle") {
            id = "io.github.ilikeyourhat.fmnw.convention.code-style-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.CodeStyleConventionPlugin"
        }
        register("compose") {
            id = "io.github.ilikeyourhat.fmnw.convention.compose-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.ComposeConventionPlugin"
        }
        register("coroutines") {
            id = "io.github.ilikeyourhat.fmnw.convention.coroutines-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.CoroutinesConventionPlugin"
        }
        register("jvm") {
            id = "io.github.ilikeyourhat.fmnw.convention.jvm-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.JvmConventionPlugin"
        }
        register("room") {
            id = "io.github.ilikeyourhat.fmnw.convention.room-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.RoomConventionPlugin"
        }
        register("hilt") {
            id = "io.github.ilikeyourhat.fmnw.convention.hilt-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.HiltConventionPlugin"
        }
        register("versioning") {
            id = "io.github.ilikeyourhat.fmnw.convention.versioning-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.VersioningConventionPlugin"
        }
        register("robolectric") {
            id = "io.github.ilikeyourhat.fmnw.convention.robolectric-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.RobolectricConventionPlugin"
        }
    }
}

dependencies {
    detektPlugins(libs.detekt.formatting)

    implementation(libs.android.application.plugin)
    implementation(libs.axion.release.plugin)
    implementation(libs.detekt.plugin)
    implementation(libs.hilt.plugin)
    implementation(libs.kotlin.compose.plugin)
    implementation(libs.ksp.plugin)
    implementation(libs.robolectric.plugin)
    implementation(libs.room.plugin)
}
