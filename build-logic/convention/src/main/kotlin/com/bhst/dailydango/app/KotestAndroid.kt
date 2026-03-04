package com.bhst.dailydango.app

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureKotestAndroid() {
    configureKotest()
    configureJUnitAndroid()
}

internal fun Project.configureJUnitAndroid() {
    pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension> {
            testOptions {
                unitTests.all { it.useJUnitPlatform() }
            }
        }
    }
    pluginManager.withPlugin("com.android.library") {
        extensions.configure<LibraryExtension> {
            testOptions {
                unitTests.all { it.useJUnitPlatform() }
            }
        }
    }
}