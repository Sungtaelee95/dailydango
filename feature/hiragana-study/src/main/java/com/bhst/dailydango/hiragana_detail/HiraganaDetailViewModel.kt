package com.bhst.dailydango.hiragana_detail

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.hiragana.study.R
import com.bhst.dailydango.domain.usecase.gif_uri.GifUriUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.domain.usecase.word.WordUseCase
import com.bhst.dailydango.model.result.WordResult
import com.bhst.dailydango.model.word.WordContentState
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
class HiraganaDetailViewModel @Inject constructor(
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val wordUseCase: WordUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val gifUriUseCase: GifUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _selectedRow = MutableStateFlow<String>("")
    val selectedRow: StateFlow<String> = _selectedRow.asStateFlow()

    private val _wordContentState = MutableStateFlow<List<WordContentState>>(emptyList())
    val wordContentState = _wordContentState.asStateFlow()

    fun updateSelectedRow(row: String) {
        _selectedRow.update { row }
    }

    fun updateCurrentItems(items: List<String>) {
        Log.d("lstlst" ,"items: $items")
        if (items.isEmpty()) return
        viewModelScope.launch {
            loadingDialogManager.show()
            val wordContents = mutableListOf<WordContentState>()
            items.forEach { word ->
                when (val result = wordUseCase(word)) {
                    is WordResult.Success -> {
                        wordContents.add(WordContentState.from(result.content))
                    }

                    is WordResult.Error -> {
                        Log.d("lstlst","에러발생")
                        messageManager.sendMessage(context.getString(R.string.error_message))
                    }
                }
            }
            _wordContentState.update { wordContents }
            updateUri()
            loadingDialogManager.dismiss()
        }
    }

    fun updateContent(content: WordContentState) {
        _wordContentState.update { it.map { if (it.word == content.word) content else it } }
    }


    fun playAudio(uri: Uri?) {
        viewModelScope.launch {
            uri?.let {
                audioPlayUseCase.playAudio(uri)
            } ?: run {
                messageManager.sendMessage(context.getString(R.string.error_message))
            }
        }
    }

    fun stopAudio() {
        viewModelScope.launch {
           audioPlayUseCase.release()
        }
    }

    private fun updateUri() {
        viewModelScope.launch {
            // 1. 현재 리스트의 단어(word) 목록만 먼저 가져옵니다.
            val currentWords = _wordContentState.value.map { it.word }

            currentWords.forEach { wordStr ->
                launch {
                    // 2. 시간이 걸리는 비동기 작업 수행
                    val soundUri = soundUriUseCase(wordStr)
                    val writeGif = gifUriUseCase(wordStr)

                    // 3. update 블록 내부에서 '가장 최신 상태'를 찾아 덮어씁니다.
                    _wordContentState.update { currentList ->
                        currentList.map { currentItem ->
                            if (currentItem.word == wordStr) {
                                // 사용자가 그 사이 isOpen을 true로 바꿨더라도 currentItem에는 반영되어 있음
                                currentItem.copy(
                                    wordSoundUri = soundUri,
                                    writeGifUri = writeGif
                                )
                            } else {
                                currentItem
                            }
                        }
                    }
                }
            }
        }
    }
}