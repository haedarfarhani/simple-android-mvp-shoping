plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    kotlin("kapt")
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.objectbox.gradle.plugin)
    }
}

apply(plugin = "io.objectbox")

android {
    namespace = "com.heydar.simplemvp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.heydar.simplemvp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // reactive
    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    // dependency injection
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // ObjectBox
    implementation(libs.objectbox.android)
    implementation(libs.objectbox.rxjava3)
    annotationProcessor(libs.objectbox.processor)

    // parser
    implementation(libs.gson)

    //networking
    implementation(libs.okhttp)
    implementation(libs.retrofit)
    implementation(libs.adapter.rxjava3)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    implementation(libs.timber)
}
