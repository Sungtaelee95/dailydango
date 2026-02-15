import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureRoborazzi
import com.bhst.dailydango.app.findLibrary

plugins {
    id("bhst.dailydango.android.library")
    id("bhst.dailydango.android.compose")
}

android {
    packaging {
        resources {
            excludes.add("META-INF/**")
        }
    }
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()
configureRoborazzi()

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:ui"))


    implementation(findLibrary("hilt.navigation.compose"))
    implementation(findLibrary("androidx.compose.navigation"))
    androidTestImplementation(findLibrary("androidx.compose.navigation.test"))

    implementation(findLibrary("androidx.lifecycle.viewModelCompose"))
    implementation(findLibrary("androidx.lifecycle.runtimeCompose"))
}
