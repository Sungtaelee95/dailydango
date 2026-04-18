package com.bhst.dailydango.conversation.conversation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.conversation.R
import com.bhst.dailydango.domain.usecase.conversation.ConversationUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.player.AudiosPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.model.conversation.ConversationContentState
import com.bhst.dailydango.model.conversation.ConversationContentUri
import com.bhst.dailydango.model.result.ConversationContentResult
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

class ConversationViewModel @Inject constructor(
    private val conversationUseCase: ConversationUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val audiosPlayUseCase: AudiosPlayUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<ConversationContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    private val _isStateAllOpen = MutableStateFlow(false)
    val isStateAllOpen = _isStateAllOpen.asStateFlow()

    init {
        collectOpenState()
    }

    fun updateConversationContent(content: ConversationContentState) {
        viewModelScope.launch {
            loadingDialogManager.show()
            _uiState.update { currentList ->
                currentList.map {
                    if (it.id == content.id) {
                        content // 누른 아이템만 업데이트된 상태로 교체
                    } else {
                        it
                    }
                }
            }
            loadingDialogManager.dismiss()
        }
    }

    fun getConversationContent(chapter: Int) {
        if (_uiState.value.isNotEmpty()) return
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = conversationUseCase(chapter)) {
                is ConversationContentResult.Success -> {
                    val contents = result.data
                    _uiState.update { contents.map { ConversationContentState.from(it) } }
                    contents.forEach { content ->
                        launch {
                            val contentUri = ConversationContentUri(
                                explanationSoundUri1 = soundUriUseCase(content.exampleForJapanese1),
                                explanationSoundUri2 = soundUriUseCase(content.exampleForJapanese2),
                                explanationSoundUri3 = soundUriUseCase(content.exampleForJapanese3),
                                explanationSoundUri4 = soundUriUseCase(content.exampleForJapanese4),
                                explanationSoundUri5 = soundUriUseCase(content.exampleForJapanese5),
                                explanationSoundUri6 = soundUriUseCase(content.exampleForJapanese6),
                                explanationSoundUri7 = soundUriUseCase(content.exampleForJapanese7),
                                explanationSoundUri8 = soundUriUseCase(content.exampleForJapanese8),
                                explanationSoundUri9 = soundUriUseCase(content.exampleForJapanese9),
                                explanationSoundUri10 = soundUriUseCase(content.exampleForJapanese10)
                            )
                            _uiState.update { currentList ->
                                currentList.map {
                                    if (it.id == content.id) {
                                        it.copy(contentUri = contentUri)
                                    } else {
                                        it
                                    }
                                }
                            }
                        }
                    }
                }

                is ConversationContentResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                    loadingDialogManager.dismiss()
                    return@launch
                }
            }
            loadingDialogManager.dismiss()
        }
    }

    fun soundPlayForContents(uris: List<Uri>) {
        viewModelScope.launch {
            loadingDialogManager.show()
            audiosPlayUseCase.playAudios(uris)
            loadingDialogManager.dismiss()
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

    fun changeOpenState() {
        _isStateAllOpen.update { !it }
    }

    private fun collectOpenState() {
        viewModelScope.launch {
            isStateAllOpen.collect { state ->
                _uiState.update { currentList ->
                    currentList.map {
                        it.copy(isOpen = state)
                    }
                }
            }
        }
    }
}