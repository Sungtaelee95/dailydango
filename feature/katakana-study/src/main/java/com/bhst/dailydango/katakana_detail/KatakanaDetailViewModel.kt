package com.bhst.dailydango.katakana_detail

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.katakana.study.R
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
class KatakanaDetailViewModel @Inject constructor(
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val wordUseCase: WordUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val gifUriUseCase: GifUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    @param:ApplicationContext private val context: Context
): ViewModel() {
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
            _wordContentState.value.forEach { content ->
                launch {
                    val newContent = content.copy(
                        wordSoundUri = soundUriUseCase(content.word),
                        writeGifUri = gifUriUseCase(content.word)
                    )
                    _wordContentState.update { wordContents ->
                        wordContents.map { if (it.word == newContent.word) newContent else it }
                    }
                }
            }
        }
    }

}