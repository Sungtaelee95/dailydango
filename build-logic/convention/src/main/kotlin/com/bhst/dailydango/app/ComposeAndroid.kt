package com.bhst.dailydango.app

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureComposeAndroid() {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    // 의존성 설정은 Project 레벨에서 바로 적용
    dependencies {
        val bom = findLibrary("androidx-compose-bom")
        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        add("implementation", findLibrary("androidx.compose.material3"))
        add("implementation", findLibrary("androidx.compose.ui"))
        add("implementation", findLibrary("androidx.compose.ui.tooling.preview"))
        add("implementation", findLibrary("compose.furiganable"))
        add("androidTestImplementation", findLibrary("androidx.test.ext"))
        add("androidTestImplementation", findLibrary("androidx.test.espresso.core"))
        add("androidTestImplementation", findLibrary("androidx.compose.ui.test"))
        add("debugImplementation", findLibrary("androidx.compose.ui.tooling"))
        add("debugImplementation", findLibrary("androidx.compose.ui.testManifest"))
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        includeSourceInformation.set(true)
    }
}

fun Project.configureComposeFeature() {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
}