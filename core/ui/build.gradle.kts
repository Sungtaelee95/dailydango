import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
    alias(libs.plugins.dailydango.android.compose)
}

android {
    setNamespace("core.ui")
}

dependencies {
    implementation(projects.core.designsystem)
}
