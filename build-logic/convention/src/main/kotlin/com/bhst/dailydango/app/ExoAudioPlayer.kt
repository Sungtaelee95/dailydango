package com.bhst.dailydango.app

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureMedia3ExoPlayer() {
    dependencies {
        // libs.versions.toml에 선언한 이름(media3-exoplayer)을 기반으로 가져옵니다.
        // TOML에서 '-'는 플러그인 코드에서 보통 '.'으로 치환됩니다.
        "implementation"(findLibrary("media3.exoplayer"))
    }
}

internal class Media3ExoPlayerPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureMedia3ExoPlayer()
        }
    }
}