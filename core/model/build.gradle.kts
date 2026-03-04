import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.library)
    alias(libs.plugins.dailydango.kotlin.library.serialization)
}

kotlin {
    setNamespace("core.model")
}