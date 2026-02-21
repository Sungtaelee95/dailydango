import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
    alias(libs.plugins.dailydango.android.compose)
}

setNamespace("core.routeapi")

dependencies {
    implementation(projects.core.designsystem)
    implementation(libs.androidx.navigation3.runtime)
}
