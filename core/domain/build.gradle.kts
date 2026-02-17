import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
}
kotlin {
    setNamespace("core.domain")
}
dependencies {
    implementation(libs.inject)
    implementation(projects.core.model)
}