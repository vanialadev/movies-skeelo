import java.io.File
import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

fun getTmdbKey(): String {
    val properties = Properties()
    val propertiesFile = File("local.properties")
    propertiesFile.inputStream().use { fileInputStream ->
        properties.load(fileInputStream)
    }
    return properties.getProperty("TMDB_KEY")
}

android {
    namespace = "com.vaniala.movies.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", getTmdbKey())
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
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
