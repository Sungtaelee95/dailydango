import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.menu")
}

dependencies {
    implementation(projects.feature.menuApi)
}