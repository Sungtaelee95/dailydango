package com.bhst.dailydango.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingDialogManager @Inject constructor(

) {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    suspend fun show() {
        _isLoading.emit(true)
    }

    suspend fun dismiss() {
        _isLoading.emit(false)
    }
}