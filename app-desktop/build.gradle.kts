import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
}

group = "pro.stuermer.bookii"
version = "1.0.0-SNAPSHOT"

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(compose.desktop.currentOs)

                implementation(libs.compose.components.splitpane)

                // TODO: use jewel dependency once published https://github.com/JetBrains/jewel
                implementation(files("../libs/compose-utils-0.0.1-SNAPSHOT.jar"))
                implementation(files("../libs/core-0.0.1-SNAPSHOT.jar"))
                implementation(files("../libs/darcula-standalone-0.0.1-SNAPSHOT.jar"))
                implementation(files("../libs/new-ui-desktop-0.0.1-SNAPSHOT.jar"))
                implementation(files("../libs/new-ui-standalone-0.0.1-SNAPSHOT.jar"))
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "pro.stuermer.bookii.MainKt"
        buildTypes.release.proguard {
            configurationFiles.from(project.file("app-desktop.pro"))
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "BookiiRecorder"
            packageVersion = "1.0.0"
            description = "Recorder for Bookii Application"
            vendor = "StürmerBenjamin"

            macOS {
                iconFile.set(project.file("icon.icns"))
            }
            windows {
                iconFile.set(project.file("icon.ico"))
                dirChooser = true
                upgradeUuid = " 75a76b31-9e89-46a3-a846-8311850ea05c "
            }
            linux {
                iconFile.set(project.file("icon.png"))
            }
        }
    }
}
