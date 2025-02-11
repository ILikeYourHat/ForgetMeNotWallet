plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("compose") {
            id = "io.github.ilikeyourhat.fmnw.convention.compose-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.ComposeConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.application.plugin)
    implementation(libs.kotlin.compose.plugin)
}
