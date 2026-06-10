import com.bhst.dailydango.app.configureCoroutineAndroid
import com.bhst.dailydango.app.configureFirebase
import com.bhst.dailydango.app.configureHiltAndroid
import com.bhst.dailydango.app.configureKotest
import com.bhst.dailydango.app.configureKotlinAndroid
import com.bhst.dailydango.app.configureMedia3ExoPlayer
import com.bhst.dailydango.app.configureMock
import com.bhst.dailydango.app.configureRoomAndroid

plugins {
    id("com.android.library")
}

configureKotlinAndroid()
configureKotest()
configureMock()
configureCoroutineAndroid()
configureHiltAndroid()
configureMedia3ExoPlayer()
configureRoomAndroid()
configureFirebase()