pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "dailydango"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

include(":baselineprofile")

include(":core:data")
include(":core:ui")
include(":core:designsystem")

include(":feature:main")

