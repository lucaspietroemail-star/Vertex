pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.library") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
            if (requested.id.id == "org.jetbrains.kotlin.plugin.compose") {
                useModule("org.jetbrains.kotlin:compose-compiler-gradle-plugin:${requested.version}")
            }
        }
    }
    repositories {
        google()
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

rootProject.name = "Vertex"
include(":seed")
include(":app")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:designsystem")
include(":core:security")
include(":feature_chat")
include(":feature_agents")
include(":feature_projects")
include(":feature_memory")
include(":feature_files")
include(":feature_settings")
include(":feature_profile")
