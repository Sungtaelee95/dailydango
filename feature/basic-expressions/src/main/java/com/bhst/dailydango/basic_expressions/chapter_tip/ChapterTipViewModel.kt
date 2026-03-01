package com.bhst.dailydango.basic_expressions.chapter_tip

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.tip.ChapterTipUseCase
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
class ChapterTipViewModel @Inject constructor(
    private val chapterTipUseCase: ChapterTipUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Tip>>(emptyList())
    val uiState: StateFlow<List<Tip>> = _uiState

    fun getChapterTip(chapter: Int) {
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = chapterTipUseCase(chapter = chapter)) {
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