import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.level.test")
}

dependencies {
    implementation(projects.feature.levelTestApi)
}