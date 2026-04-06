import com.bhst.dailydango.app.configureFirebase
import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureKotestAndroid
import com.bhst.dailydango.app.configureKotlinAndroid
import com.bhst.dailydango.app.configureMedia3ExoPlayer


plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()
configureFirebase()
configureMedia3ExoPlayer()
