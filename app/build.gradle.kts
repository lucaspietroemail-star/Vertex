plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.vertex.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.vertex.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    buildFeatures { compose = true }

}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:designsystem"))
    implementation(project(":feature_chat"))
    implementation(project(":feature_agents"))
    implementation(project(":feature_projects"))
    implementation(project(":feature_memory"))
    implementation(project(":feature_files"))
    implementation(project(":feature_settings"))
    implementation(project(":feature_profile"))
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.ui:ui:1.7.6")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.6")
    implementation("androidx.navigation:navigation-compose:2.8.5")
}
