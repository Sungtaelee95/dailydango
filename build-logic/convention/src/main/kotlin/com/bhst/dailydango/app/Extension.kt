package com.bhst.dailydango.app

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

// val 대신 설정 블록을 바로 실행하는 함수로 변경합니다.
internal val ExtensionContainer.libs: VersionCatalog
    get() = getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.findLibrary(name: String): Provider<MinimalExternalModuleDependency> =
    extensions.libs.findLibrary(name).get()

internal fun Project.findVersion(name: String): String =
    extensions.libs.findVersion(name).get().requiredVersion