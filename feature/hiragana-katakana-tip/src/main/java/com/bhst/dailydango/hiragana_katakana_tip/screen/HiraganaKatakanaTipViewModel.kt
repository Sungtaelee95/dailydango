package com.bhst.dailydango.hiragana_katakana_tip.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.tip.HiraganaTipUseCase
import com.bhst.dailydango.domain.usecase.tip.KatakanaTipUseCase
import com.bhst.dailydango.model.result.TipResult
import com.bhst.dailydango.model.tip.Tip
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiraganaKatakanaTipViewModel @Inject constructor(
    private val hiraganaTipUseCase: HiraganaTipUseCase,
    private val katakanaTipUseCase: KatakanaTipUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @param: ApplicationContext private val context: Context
): ViewModel() {
    private val _hiraganaTips = MutableStateFlow<List<Tip>>(emptyList())
    val hiraganaTips: StateFlow<List<Tip>> = _hiraganaTips.asStateFlow()

    private val _katakanaTips = MutableStateFlow<List<Tip>>(emptyList())
    val katakanaTips: StateFlow<List<Tip>> = _katakanaTips.asStateFlow()

    fun getTips() {
        viewModelScope.launch {
            loadingDialogManager.show()
            val hiraganaResult = hiraganaTipUseCase()
            val katakanaResult = katakanaTipUseCase()

            when (hiraganaResult) {
                is TipResult.Success -> {
                    _hiraganaTips.update { hiraganaResult.tipList }
                }
                is TipResult.Error -> {
                    // 에러 처리
                }
            }

            when (katakanaResult) {
                is TipResult.Success -> {
                    _katakanaTips.update { katakanaResult.tipList }
                }
                is TipResult.Error -> {
                    // 에러 처리
                }
            }
            loadingDialogManager.dismiss()
        }
    }

}