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
include(":core:domain")
include(":core:model")
include(":core:route-api")
include(":core:ad_mob")

include(":feature:main")
include(":feature:home-api")
include(":feature:home")
include(":feature:hiragana-study-api")
include(":feature:hiragana-study")
include(":feature:menu-api")
include(":feature:menu")
include(":feature:search-api")
include(":feature:search")
include(":feature:katakana-study-api")
include(":feature:katakana-study")
include(":feature:basic-expressions-api")
include(":feature:basic-expressions")
include(":feature:level-test-api")
include(":feature:level-test")

