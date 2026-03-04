import com.bhst.dailydango.app.configureFirebase
import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureKotestAndroid
import com.bhst.dailydango.app.configureKotlinAndroid
import com.bhst.dailydango.app.configureMedia3ExoPlayer
import com.bhst.dailydango.app.configureRoborazzi


plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
configureRoborazzi()
configureFirebase()
configureMedia3ExoPlayer()
