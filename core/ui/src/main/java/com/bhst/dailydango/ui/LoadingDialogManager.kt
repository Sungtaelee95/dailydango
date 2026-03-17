package com.bhst.dailydango.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoadingDialogManager @Inject constructor(

) {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    fun show() {
        _isLoading.update { true }
    }

    // 🚨 suspend 키워드 제거
    fun dismiss() {
        _isLoading.update { false }
    }
}