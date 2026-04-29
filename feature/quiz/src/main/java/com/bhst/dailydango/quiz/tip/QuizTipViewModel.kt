package com.bhst.dailydango.quiz.tip

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.quiz.R
import com.bhst.dailydango.domain.usecase.tip.QuizTipUseCase
import com.bhst.dailydango.model.result.TipResult
import com.bhst.dailydango.model.tip.Tip
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizTipViewModel @Inject constructor(
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val quizTipUseCase: QuizTipUseCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Tip>>(emptyList())
    val uiState: StateFlow<List<Tip>> = _uiState

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun getQuizTips() {
        if (_uiState.value.isNotEmpty()) return
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = quizTipUseCase()) {
                is TipResult.Success -> {
                    val tips = result.tipList
                    _uiState.emit(tips.sortedBy { it.order })
                }

                is TipResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }
}