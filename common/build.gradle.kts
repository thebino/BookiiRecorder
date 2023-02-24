plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
}

group = "pro.stuermer.bookii"
version = "1.0.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        jvmToolchain(11)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                implementation("io.github.oshai:kotlin-logging-jvm:4.0.0-beta-23")

//                implementation("org.usb4java:usb4java:1.3.0")
                implementation("net.samuelcampos:usbdrivedetector:2.2.1")
                implementation(libs.square.okio)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.square.okio.fakefilesystem)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.core.ktx)
            }
        }
        val androidInstrumentedTest by getting {
            dependencies {
                implementation(libs.junit)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    compileSdk = 33
    namespace = "pro.stuermer.bookii.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
