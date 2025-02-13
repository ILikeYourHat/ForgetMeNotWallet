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
        register("room") {
            id = "io.github.ilikeyourhat.fmnw.convention.room-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.RoomConventionPlugin"
        }
        register("hilt") {
            id = "io.github.ilikeyourhat.fmnw.convention.hilt-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.HiltConventionPlugin"
        }
        register("robolectric") {
            id = "io.github.ilikeyourhat.fmnw.convention.robolectric-convention-plugin"
            implementationClass = "io.github.ilikeyourhat.fmnw.convention.RobolectricConventionPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.application.plugin)
    implementation(libs.hilt.plugin)
    implementation(libs.kotlin.compose.plugin)
    implementation(libs.ksp.plugin)
    implementation(libs.robolectric.plugin)
    implementation(libs.room.plugin)
}
