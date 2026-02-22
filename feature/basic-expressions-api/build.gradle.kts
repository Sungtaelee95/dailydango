import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
    alias(libs.plugins.dailydango.kotlin.library.serialization)
}

android {
    setNamespace("feature.basic.expressions.study.api")
}
