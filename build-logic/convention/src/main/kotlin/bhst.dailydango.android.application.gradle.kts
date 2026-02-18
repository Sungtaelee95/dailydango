import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureKotestAndroid
import com.bhst.dailydango.app.configureKotlinAndroid
import com.bhst.dailydango.app.configureRoborazzi
import com.bhst.dailydango.app.findLibrary
import gradle.kotlin.dsl.accessors._9be691093bbb1cd371308cff38741322.implementation

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
configureRoborazzi()
