// 1. plugins 블록이 가장 먼저 나와야 합니다.

import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {

    setNamespace("feature.main")

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.feature.homeApi)
    implementation(projects.feature.home)
    implementation(projects.feature.hiraganaStudyApi)
    implementation(projects.feature.hiraganaStudy)
    implementation(projects.feature.katakanaStudyApi)
    implementation(projects.feature.katakanaStudy)
    implementation(projects.feature.basicExpressionsApi)
    implementation(projects.feature.basicExpressions)
    implementation(projects.feature.levelTestApi)
    implementation(projects.feature.levelTest)
    implementation(projects.feature.menuApi)
    implementation(projects.feature.menu)
    implementation(projects.feature.searchApi)
    implementation(projects.feature.search)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation3.ui)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.androidx.core.splashscreen)
}