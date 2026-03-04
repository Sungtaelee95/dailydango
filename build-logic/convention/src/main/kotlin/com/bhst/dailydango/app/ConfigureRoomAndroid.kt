package com.bhst.dailydango.app

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureRoomAndroid() {
    // Room 컴파일러를 사용하기 위해 KSP 플러그인을 적용합니다.
    pluginManager.apply("com.google.devtools.ksp")

    dependencies {
        // libs.versions.toml에 선언된 Room 의존성을 추가합니다.
        // TOML의 'androidx-room-runtime' 등은 보통 '.'으로 치환하여 찾습니다.
        "implementation"(findLibrary("androidx.room.runtime"))
        "implementation"(findLibrary("androidx.room.ktx"))
        "ksp"(findLibrary("androidx.room.compiler"))
    }
}

internal class RoomAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureRoomAndroid()
        }
    }
}