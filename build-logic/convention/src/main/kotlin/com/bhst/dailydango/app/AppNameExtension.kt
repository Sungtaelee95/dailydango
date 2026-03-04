package com.bhst.dailydango.app

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.setNamespace(name: String) {
    pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension> {
            namespace = "com.bhst.dailydango.app.$name"
        }
    }
    pluginManager.withPlugin("com.android.library") {
        extensions.configure<LibraryExtension> {
            namespace = "com.bhst.dailydango.app.$name"
        }
    }
}
