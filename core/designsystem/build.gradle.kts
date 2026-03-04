import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
    alias(libs.plugins.dailydango.android.compose)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    setNamespace("core.designsystem")
}

dependencies {
    implementation(projects.core.model)

    implementation(libs.androidx.appcompat)

    implementation(libs.coil.compose)
    implementation(libs.compose.placeholder)
    implementation(libs.landscapist.bom)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)

    implementation(libs.androidx.glance)

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.room.ktx)
}
