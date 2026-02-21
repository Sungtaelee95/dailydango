import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.search")
}

dependencies {
    implementation(projects.feature.searchApi)
}