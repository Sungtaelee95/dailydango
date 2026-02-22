import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
}

android {
    setNamespace("feature.basic.expressions")
}

dependencies {
    implementation(projects.feature.basicExpressionsApi)
}