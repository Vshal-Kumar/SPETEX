// Root build.gradle.kts

plugins {
    id("com.android.application") version "8.6.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    alias(libs.plugins.google.gms.google.services) apply false // Kotlin plugin for Android
}

// No repositories block here; they are defined in settings.gradle.kts
