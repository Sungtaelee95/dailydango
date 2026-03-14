package com.bhst.dailydango.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.bhst.dailydango.ad_mob.loadInterstitialAd
import com.bhst.dailydango.ad_mob.showInterstitialAd
import com.bhst.dailydango.designsystem.component.DailyDangoTopAppBar
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.menu_api.MenuRoute
import com.bhst.dailydango.model.theme.config.ThemeConfig
import com.bhst.dailydango.ui.GlobalLoadingDialog
import com.bhst.dailydango.ui.GlobalMessageToast
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import com.google.android.gms.ads.interstitial.InterstitialAd
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loadingDialogManager: LoadingDialogManager

    @Inject
    lateinit var messageManager: MessageManager

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            val themeConfig by viewModel.themeConfig.collectAsStateWithLifecycle(
                ThemeConfig.SYSTEM,
                this
            )
            var interstitialAd by remember { mutableStateOf<InterstitialAd?>(null) }
            val context = LocalContext.current

            // 화면이 켜지자마자 백그라운드에서 조용히 광고를 불러옵니다.
            LaunchedEffect(Unit) {
                loadInterstitialAd(context) { loadedAd ->
                    interstitialAd = loadedAd
                }
            }

            val isDark = when (themeConfig) {
                ThemeConfig.SYSTEM -> isSystemInDarkTheme()
                ThemeConfig.DARK -> true
                ThemeConfig.LIGHT -> false
            }

            DailyDangoTheme(
                darkTheme = isDark
            ) {
                val appState = rememberDailyDangoAppState()
                val entryProvider = remember(appState) {
                    dailyDangoEntryProvider(
                        navigateTo = appState::navigationTo, // 함수 참조
                        back = appState::onBack        // 함수 참조
                    )
                }
                val lastBackStack = appState.backStack.lastOrNull()
                Scaffold(
                    bottomBar = {
                        AdmobBanner()
                    },
                    topBar = {
                        val type = lastBackStack?.getTopBar() ?: TopAppBarNavigationType.None
                        DailyDangoTopAppBar(
                            modifier = Modifier.padding(top = 28.dp),
                            navigationType = type,
                            onNavigationClick = appState::onBack,
                            onActionClick = { appState.navigationTo(MenuRoute) }
                        )
                    }
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        backStack = appState.backStack,
                        onBack = {
                            appState.onBack()
                            if (appState.backStack.size == 2) {
                                showInterstitialAd(
                                    context = context,
                                    ad = interstitialAd,
                                    onAdDismissed = {
                                        interstitialAd = null
                                    }
                                )
                            }
                        },
                        entryProvider = entryProvider,
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator()
                        )
                    )
                    GlobalLoadingDialog(loadingDialogManager = loadingDialogManager)
                    GlobalMessageToast(messageManager = messageManager)
                }
            }
        }
    }
}