package com.bhst.dailydango.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.bhst.dailydango.designsystem.component.DailyDangoTopAppBar
import com.bhst.dailydango.designsystem.component.TopAppBarNavigationType
import com.bhst.dailydango.designsystem.theme.DailyDangoTheme
import com.bhst.dailydango.menu_api.MenuRoute
import com.bhst.dailydango.ui.GlobalLoadingDialog
import com.bhst.dailydango.ui.MessageManager
import com.bhst.dailydango.ui.GlobalMessageToast
import com.bhst.dailydango.ui.LoadingDialogManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var loadingDialogManager: LoadingDialogManager

    @Inject
    lateinit var messageManager: MessageManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            DailyDangoTheme {
                val appState = rememberDailyDangoAppState()
                val entryProvider = remember(appState) {
                    dailyDangoEntryProvider(
                        navigateTo = appState::navigationTo, // 함수 참조
                        back = appState::onBack        // 함수 참조
                    )
                }
                val lastBackStack = appState.backStack.lastOrNull()
                Scaffold(
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
                        onBack = { appState.onBack() },
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