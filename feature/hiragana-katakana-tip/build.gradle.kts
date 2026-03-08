import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    setNamespace("feature.hiragana.katakana.tip")
}

dependencies {
    implementation(projects.feature.hiraganaKatakanaTipApi)
}