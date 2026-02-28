package com.bhst.dailydango.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun GlobalMessageToast(
    messageManager: MessageManager,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        messageManager.message.collect {
            keyboardController?.hide()
            focusManager.clearFocus()
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}