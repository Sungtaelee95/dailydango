package com.bhst.dailydango.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.theme.SetThemeConfigUseCase
import com.bhst.dailydango.domain.usecase.theme.ThemeConfigUseCase
import com.bhst.dailydango.model.theme.config.ThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    themeConfigUseCase: ThemeConfigUseCase,
    private val setThemeConfigUseCase: SetThemeConfigUseCase
): ViewModel() {

    val themeConfig = themeConfigUseCase()

    fun updateThemeConfig(themeConfig: ThemeConfig){
        viewModelScope.launch {
            setThemeConfigUseCase(themeConfig)
        }
    }
}