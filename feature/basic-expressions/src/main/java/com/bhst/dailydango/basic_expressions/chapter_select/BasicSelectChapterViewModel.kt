package com.bhst.dailydango.basic_expressions.chapter_select

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.chapter.ChapterLimitUseCase
import com.bhst.dailydango.domain.usecase.chapter.ChapterUseCase
import com.bhst.dailydango.model.chapter.Chapter
import com.bhst.dailydango.model.result.ChapterLimitResult
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
    private val chapterLimitUseCase: ChapterLimitUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<Chapter>>(emptyList())
    val uiState: StateFlow<List<Chapter>> = _uiState

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun getChapters() {
        viewModelScope.launch {
            loadingDialogManager.show()
            var limit: Int
            when (val result = chapterLimitUseCase()) {
                is ChapterLimitResult.Success -> {
                    limit = result.data.limit
                }

                is ChapterLimitResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                    loadingDialogManager.dismiss()
                    return@launch
                }
            }
            when (val result = chapterUseCase()) {
                is ChapterResult.Success -> {
                    val chapters = result.data
                    _uiState.emit(
                        chapters
                            .filter {
                                it.title.toIntOrNull() != null && it.title.toInt() <= limit
                            }
                            .sortedBy { it.title.toInt() }
                    )
                }

                is ChapterResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }
}