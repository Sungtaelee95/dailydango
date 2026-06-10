import com.android.build.api.dsl.LibraryExtension
import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
    alias(libs.plugins.dailydango.kotlin.library.serialization)
}

extensions.configure<LibraryExtension> {
    setNamespace("feature.gatakana.study.api")
}
