package com.bhst.dailydango.play_repeat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.player.AudioRepeatUseCase
import com.bhst.dailydango.domain.usecase.player.SetAudioRepeatUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.ui.LoadingDialogManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayRepeatViewModel @Inject constructor(
    private val setAudioRepeatUseCase: SetAudioRepeatUseCase,
    private val audioRepeatUseCase: AudioRepeatUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val loadingDialogManager: LoadingDialogManager
): ViewModel() {

    private val _playRepeat = MutableStateFlow(1)
    val playRepeat: StateFlow<Int> = _playRepeat

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }


    fun getPlayRepeat() {
        viewModelScope.launch {
            audioRepeatUseCase().collect { repeat ->
                _playRepeat.update { repeat }
            }
        }
    }

    fun setPlayRepeat(repeat: Int) {
        viewModelScope.launch {
            loadingDialogManager.show()
            setAudioRepeatUseCase.setPlayRepeat(repeat)
            loadingDialogManager.dismiss()
        }
    }

    fun playTestExpression(expression: String) {
        viewModelScope.launch {
            loadingDialogManager.show()
            val uri = soundUriUseCase(expression)
            if (uri != null ) audioPlayUseCase.playAudio(uri)
            loadingDialogManager.dismiss()
        }
    }

    fun soundPlayRelease() {
        viewModelScope.launch {
            audioPlayUseCase.release()
        }
    }
}