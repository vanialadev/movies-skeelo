@file:Suppress("UnstableApiUsage")

import java.io.File
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

fun getPropertiesKey(key: String): String {
    val properties = Properties()
    val propertiesFile = File("local.properties")
    propertiesFile.inputStream().use { fileInputStream ->
        properties.load(fileInputStream)
    }
    return properties.getProperty(key)
}

android {
    namespace = "com.vaniala.movies.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getPropertiesKey("TMDB_KEY"))
        buildConfigField("Integer", "ACOUNT_ID", getPropertiesKey("ACCOUNT_ID"))
        buildConfigField("String", "SESSION_ID", getPropertiesKey("SESSION_ID"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests.all { test ->
            test.testLogging {
                events("passed", "skipped", "failed", "standardOut", "standardError")
                test.outputs.upToDateWhen { false }
                showStandardStreams = true
            }
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))

    implementation(libs.timber)
    implementation(libs.retrofit)
    implementation(libs.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.converter)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    detektPlugins(libs.detekt.formatting)

    implementation(libs.paging)
    implementation(libs.paging.runtime)

    implementation(libs.datastore)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)

    testImplementation(libs.turbine)
    testImplementation(libs.coroutines.test)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
