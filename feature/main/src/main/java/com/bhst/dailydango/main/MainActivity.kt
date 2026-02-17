package com.bhst.dailydango.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.home.HomeScreen
import com.bhst.dailydango.home_api.HomeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            DailyDangoTheme {
                val backStack = remember { mutableStateListOf<NavKey>(HomeRoute) }
                Scaffold(

                ){ innerPadding ->
                    NavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            entry<HomeRoute> {
                                HomeScreen()
                            }

                        }
                    )
                }
            }
        }
    }
}