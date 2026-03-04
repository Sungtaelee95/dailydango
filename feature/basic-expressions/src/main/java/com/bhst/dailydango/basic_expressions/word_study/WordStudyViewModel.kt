package com.bhst.dailydango.basic_expressions.word_study

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.domain.usecase.word.WordUseCase
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.content.ContentUri
import com.bhst.dailydango.model.result.SentenceContentResult
import com.bhst.dailydango.model.result.WordContentResult
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
class WordStudyViewModel @Inject constructor(
    private val wordUseCase: WordUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {
    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()


    fun getWordContent(chapter: Int) {
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = wordUseCase(chapter = chapter)) {
                is WordContentResult.Success -> {
                    // 1. 텍스트 데이터를 먼저 화면에 보여주기 위해 상태 방출
                    val initialContents = result.contents.map { ContentState.from(content = it) }
                    _uiState.emit(initialContents)

                    // 2. 이후 백그라운드에서 개별 항목의 오디오 URI를 가져와 업데이트
                    initialContents.forEach { content ->
                        launch {
                            val contentUri = ContentUri(
                                titleSoundUri = soundUriUseCase(content.japaneseTitle),
                                explanationSoundUri1 = soundUriUseCase(content.exampleForJapanese1),
                                explanationSoundUri2 = soundUriUseCase(content.exampleForJapanese2)
                            )
                            _uiState.update { currentList ->
                                currentList.map {
                                    if (it.japaneseTitle == content.japaneseTitle) {
                                        it.copy(contentUri = contentUri)
                                    } else {
                                        it
                                    }
                                }
                            }
                        }
                    }
                }

                is WordContentResult.Error -> {
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

    fun soundPlayForContent(uri: Uri?) {
        viewModelScope.launch {
            loadingDialogManager.show()
            uri?.let {
                audioPlayUseCase.playAudio(uri = uri)
            }
            loadingDialogManager.dismiss()
        }
    }

    fun soundPlayRelease() {
        viewModelScope.launch {
            audioPlayUseCase.release()
        }
    }
}