plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

group = "pro.stuermer.bookii"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))

    implementation(libs.androidx.activity.compose)

    // Coroutines
    implementation(libs.jetbrains.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)

    // dependency injection
    implementation(libs.bundles.koin)


    // ktor
    implementation(libs.bundles.ktor)
    testImplementation(libs.ktor.mock.jvm)


    // Accompanist
    implementation(libs.bundles.accompanist)

    // Testing
//    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit)
//    testImplementation(libs.androidx.test.ext.junit.ktx)
//    testImplementation(libs.mockk)
    testImplementation(libs.jetbrains.kotlinx.coroutines.test)


    // Instrumented testing
//    androidTestImplementation(libs.kotlin.test)
//    androidTestImplementation(libs.androidx.test.ext.junit)
//    androidTestImplementation(libs.androidx.test.espresso.core)
}

android {
    compileSdk = 33
    namespace = "pro.stuermer.bookii"

    defaultConfig {
        // API 12 | 3.1 | USB Host mode
        // API 24 | 7.0 | network security
        // API 26 | 8.0 | java 8 time api
        minSdk = 21
        targetSdk = 33

        versionCode = 1
        versionName = "0.1.0-SNAPSHOT"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs =
            freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs =
            freeCompilerArgs + "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api"
    }

    packagingOptions {
        resources.excludes += "META-INF/AL2.0"
        resources.excludes += "META-INF/LGPL2.1"
        resources.excludes += "META-INF/licenses/ASM"
        resources.excludes += "**/attach_hotspot_windows.dll"
    }
}
