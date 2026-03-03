package com.bhst.dailydango.basic_expressions.sentnece_study

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sentence.SentenceUseCase
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.result.SentenceContentResult
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SentenceStudyViewModel @Inject constructor(
    private val sentenceUseCase: SentenceUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val audioPlayUseCase: AudioPlayUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getSentenceContent(chapter: Int) {
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = sentenceUseCase(chapter = chapter)) {
                is SentenceContentResult.Success -> {
                    val contents = result.contents
                    _uiState.emit(contents.map { ContentState.from(it) })
                }

                is SentenceContentResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }

    fun updateSentenceContent(content: ContentState) {
        _uiState.update { currentList ->
            currentList.map {
                if (it.japaneseTitle == content.japaneseTitle) {
                    content // 누른 아이템만 업데이트된 상태로 교체
                } else {
                    it
                }
            }
        }
    }

    fun soundPlayForContent(content: String) {
        Log.d("lstlst" ,"viewModel-soundPlayForContent/ $content")
        viewModelScope.launch {
            loadingDialogManager.show()
            audioPlayUseCase.playAudio(fileName = content)
            loadingDialogManager.dismiss()
        }

    }

    fun soundPlayRelease() {
        viewModelScope.launch {
            audioPlayUseCase.release()
        }
    }
}