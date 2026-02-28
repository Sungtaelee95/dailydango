package com.bhst.dailydango.basic_expressions.chapter_select

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.chapter.ChapterUseCase
import com.bhst.dailydango.model.chapter.Chapter
import com.bhst.dailydango.model.result.ChapterResult
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicSelectChapterViewModel @Inject constructor(
    private val chapterUseCase: ChapterUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _uiState = MutableStateFlow<List<Chapter>>(emptyList())
    val uiState: StateFlow<List<Chapter>> = _uiState

    fun getChapters() {
        viewModelScope.launch {
            loadingDialogManager.show()

            when (val result = chapterUseCase()) {
                is ChapterResult.Success -> {
                    val chapters = result.data
                    _uiState.emit(chapters.sortedBy { it.title })
                }
                is ChapterResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }



}