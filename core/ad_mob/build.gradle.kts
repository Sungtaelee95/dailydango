import com.bhst.dailydango.app.setNamespace
import java.util.Properties // ✨ 꼭 임포트 해주세요!

plugins {
    alias(libs.plugins.dailydango.android.library)
}

kotlin {
    setNamespace("core.ad.mob")
}

// ✨ 1. 이 모듈에서만 local.properties를 읽어옵니다.
val properties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    properties.load(localPropertiesFile.inputStream())
}

// ✨ 2. 이 모듈에서만 BuildConfig 생성을 켭니다.
extensions.configure<com.android.build.api.dsl.LibraryExtension> {
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        val interstitialId = properties.getProperty("ADMOB_INTERSTITIAL_ID") ?: "\"ca-app-pub-3940256099942544/1033173712\""
        buildConfigField("String", "ADMOB_INTERSTITIAL_ID", interstitialId)

        val bannerlId = properties.getProperty("ADMOB_BANNER_ID") ?: ""
        buildConfigField("String", "ADMOB_BANNER_ID", bannerlId)
    }
}

dependencies {


    implementation(libs.play.services.ads.api)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}