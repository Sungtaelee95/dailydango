import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
    alias(libs.plugins.dailydango.android.compose)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    setNamespace("core.ui")
}

dependencies {
    implementation(projects.core.designsystem)
}
