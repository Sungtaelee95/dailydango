import com.android.build.api.dsl.ApplicationExtension
import com.bhst.dailydango.app.filterProject

plugins {
    alias(libs.plugins.dailydango.android.application)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.compose.compiler)
}

extensions.configure<ApplicationExtension> {
    namespace = "com.bhst.dailydango"
    compileSdk = 36

    buildFeatures {
        compose = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.bhst.dailydango"
        versionCode = 17
        versionName = "1.0.17"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

}

baselineProfile {
    warnings {
        maxAgpVersion = false
    }
}

dependencies {

    rootProject.subprojects.filterProject {
        if (it.name.contains("baselineprofile")) {
            baselineProfile(it)
        } else if (it.name.contains("testing")) {
            testImplementation(it)
        } else {
            implementation(it)
        }
    }

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))

    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    implementation(libs.play.services.ads)
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)
}
