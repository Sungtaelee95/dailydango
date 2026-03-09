import com.android.build.api.dsl.LibraryExtension
import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureRoborazzi
import com.bhst.dailydango.app.findLibrary
import gradle.kotlin.dsl.accessors._3dfc63a612bb7993dc38df28b51798c6.implementation

plugins {
    id("bhst.dailydango.android.library")
    id("bhst.dailydango.android.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

extensions.configure<LibraryExtension> {
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
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:route-api"))
    implementation(project(":core:ad_mob"))
    implementation(project(":core:util"))

    // --- Navigation 3 Dependencies ---
    // 기존 androidx.compose.navigation 제거 후 교체
    implementation(findLibrary("androidx.navigation3.runtime"))
    implementation(findLibrary("androidx.navigation3.ui"))

    // Navigation3용 ViewModel 지원 (LocalViewModelStoreOwner 제공 등)
    implementation(findLibrary("androidx.lifecycle.viewmodel.navigation3"))

    // (선택) Material3 Adaptive 지원 (대화면/폴더블 대응 시 필요)
    implementation(findLibrary("androidx.material3.adaptive.navigation3"))

    // Type-safe Route 정의를 위한 Serialization Core
    implementation(findLibrary("kotlinx.serialization.core"))

    // ---------------------------------

    // Hilt Navigation (ViewModel 주입을 위해 유지)
    implementation(findLibrary("hilt.navigation.compose"))

    implementation(findLibrary("androidx.lifecycle.viewModelCompose"))
    implementation(findLibrary("androidx.lifecycle.runtimeCompose"))

    // 애드몹
    implementation(findLibrary("play.services.ads"))
}
