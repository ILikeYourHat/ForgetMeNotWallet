plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.android.junit5)
    alias(libs.plugins.convention.codestyle)
    alias(libs.plugins.convention.compose)
    alias(libs.plugins.convention.coroutines)
    alias(libs.plugins.convention.hilt)
    alias(libs.plugins.convention.jvm)
    alias(libs.plugins.convention.robolectric)
    alias(libs.plugins.convention.room)
    alias(libs.plugins.convention.versioning)
}

android {
    namespace = "io.github.ilikeyourhat.fmnw"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.github.ilikeyourhat.fmnw"
        minSdk = 26
        targetSdk = 35
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.composed.barcodes)
    implementation(libs.speeddial.bottomappbar.material3)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.liveevent)
    implementation(libs.androidx.compose.camera.viewfinder)
    implementation(libs.gms.scanner)

    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.mockk)
    testImplementation(libs.kotest.assertions.core)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
