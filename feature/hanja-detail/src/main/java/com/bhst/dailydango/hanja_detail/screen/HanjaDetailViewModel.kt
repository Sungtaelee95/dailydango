package com.bhst.dailydango.hanja_detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.hanja.HanjaUseCase
import com.bhst.dailydango.model.hanja.HanjaContent
import com.bhst.dailydango.model.result.HanjaResult
import com.bhst.dailydango.ui.LoadingDialogManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HanjaDetailViewModel @Inject constructor(
    private val hanjaUseCase: HanjaUseCase,
    private val loadingDialogManager: LoadingDialogManager
) : ViewModel() {
    private val _hanjaContents = MutableStateFlow<List<HanjaContent>>(emptyList())
    val hanjaContents = _hanjaContents.asStateFlow()

    fun getHanjaContents(hanjas: List<String>) {
        viewModelScope.launch {
            loadingDialogManager.show()
            hanjas.forEach { hanja ->
                when (val result = hanjaUseCase(hanja)) {
                    is HanjaResult.Success -> {
                        result.content?.let { content ->
                            _hanjaContents.update { it + content }
                        }
                    }
                    is HanjaResult.Error -> {

                    }
                }
            }
            loadingDialogManager.dismiss()
        }
    }
}