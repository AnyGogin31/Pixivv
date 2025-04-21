pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://packages.jetbrains.team/maven/p/amper/amper")
        maven("https://www.jetbrains.com/intellij-repository/releases")
        maven("https://packages.jetbrains.team/maven/p/ij/intellij-dependencies")
    }
}

plugins {
    id("org.jetbrains.amper.settings.plugin").version("0.6.0")
}

include(":app-android")
include(":app-ios")
include(":app-jvm")

include(":core-brotli")
include(":core-remote-client")
include(":core-remote-constants")
include(":core-remote-engine")
include(":core-remote-utils")
include(":core-storage")

include(":data-remote")

include(":shared-entry")
