import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    setNamespace("feature.katakana.study")
}

dependencies {
    implementation(projects.feature.katakanaStudyApi)
}