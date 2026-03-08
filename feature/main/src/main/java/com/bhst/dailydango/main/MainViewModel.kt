package com.bhst.dailydango.main

import androidx.lifecycle.ViewModel
import com.bhst.dailydango.domain.usecase.theme.ThemeConfigUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeConfigUseCase: ThemeConfigUseCase
) : ViewModel() {

    val themeConfig = themeConfigUseCase()

}