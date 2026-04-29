import com.bhst.dailydango.app.setNamespace

plugins {
    alias(libs.plugins.dailydango.android.feature)
    alias(libs.plugins.dailydango.kotlin.library.serialization)
}

extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    setNamespace("feature.level.quiz.api")
}