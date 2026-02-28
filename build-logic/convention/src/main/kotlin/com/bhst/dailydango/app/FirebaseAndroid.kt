package com.bhst.dailydango.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureFirebase() {
    // 앱 모듈인 경우에만 Google Services 플러그인을 적용합니다.
    // (라이브러리 모듈에 적용되어 google-services.json 누락 에러가 발생하는 것을 방지)
    pluginManager.withPlugin("com.android.application") {
        pluginManager.apply("com.google.gms.google-services")
    }

    dependencies {
        // Version Catalog에서 정의한 이름을 바탕으로 라이브러리를 찾습니다.
        val bom = findLibrary("firebase.bom")

        // BoM 적용
        add("implementation", platform(bom))

        // Firestore 및 Storage 추가 (BoM 덕분에 버전은 생략됩니다)
        add("implementation", findLibrary("firebase.firestore"))
        add("implementation", findLibrary("firebase.storage"))
    }
}