plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.myapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout.v220)
    implementation(libs.annotation)
    implementation(libs.viewpager2)
    implementation(libs.recyclerview)
    implementation(libs.retrofit) // Ensure this is included
    implementation(libs.converter.gson) // Gson converter for Retrofit
    implementation(libs.okhttp) // For making HTTP requests
    implementation(libs.logging.interceptor) // Optional: For logging HTTP requests
    implementation(libs.firebase.core)
    implementation(libs.firebase.ml.natural.language)
    implementation(libs.translate)
    implementation(libs.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
}