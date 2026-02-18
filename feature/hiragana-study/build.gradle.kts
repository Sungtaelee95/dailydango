import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.hiragana.study")
}

dependencies {
    implementation(projects.feature.hiraganaStudyApi)
}