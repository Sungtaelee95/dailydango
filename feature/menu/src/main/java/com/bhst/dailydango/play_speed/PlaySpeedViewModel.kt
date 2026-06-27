package com.bhst.dailydango.play_speed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.player.AudioRepeatUseCase
import com.bhst.dailydango.domain.usecase.player.AudioSpeedUseCase
import com.bhst.dailydango.domain.usecase.player.SetAudioRepeatUseCase
import com.bhst.dailydango.domain.usecase.player.SetAudioSpeedUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.ui.LoadingDialogManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaySpeedViewModel @Inject constructor(
    private val setAudioSpeedUseCase: SetAudioSpeedUseCase,
    private val audioSpeedUseCase: AudioSpeedUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val setAudioRepeatUseCase: SetAudioRepeatUseCase,
    private val audioRepeatUseCase: AudioRepeatUseCase,
) : ViewModel() {
    private val _playSpeed = MutableStateFlow(1.0f)
    val playSpeed: StateFlow<Float> = _playSpeed

    private val _playRepeat = MutableStateFlow(1)
    val playRepeat: StateFlow<Int> = _playRepeat

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

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun getPlaySpeed() {
        viewModelScope.launch {
            audioSpeedUseCase().collect { speed ->
                _playSpeed.update { speed }
            }
        }
    }

    fun setPlaySpeed(speed: Float) {
        viewModelScope.launch {
            loadingDialogManager.show()
            setAudioSpeedUseCase(speed)
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