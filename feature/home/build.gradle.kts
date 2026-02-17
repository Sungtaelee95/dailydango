import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.home")
}

dependencies {
    implementation(projects.feature.homeApi)
}