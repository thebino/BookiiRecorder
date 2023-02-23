pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            mavenLocal()
            mavenCentral()
            google()
            maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        }
    }

//    plugins {
//        kotlin("multiplatform")//.version(extra["kotlin.version"] as String)
//        kotlin("android")//.version(extra["kotlin.version"] as String)
//        id("com.android.application")//.version(extra["agp.version"] as String)
//        id("com.android.library")//.version(extra["agp.version"] as String)
//        id("org.jetbrains.compose")//.version(extra["compose.version"] as String)
//    }
}

rootProject.name = "BookiiRecorder"

include(":app-android")
include(":app-desktop")
include(":common")
