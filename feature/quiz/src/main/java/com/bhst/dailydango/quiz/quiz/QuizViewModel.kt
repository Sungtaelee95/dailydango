package com.bhst.dailydango.quiz.quiz

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.quiz.R
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.quiz.QuizUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.model.quiz.QuizContentState
import com.bhst.dailydango.model.result.QuizResult
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
class QuizViewModel @Inject constructor(
    private val quizUseCase: QuizUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @param:ApplicationContext private val context: Context
) : ViewModel() {

    private val _quizContents = MutableStateFlow<List<QuizContentState>>(emptyList())
    val quizContents = _quizContents.asStateFlow()

    fun getQuizContent(chapter: Int) {
        if (_quizContents.value.isNotEmpty()) return
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = quizUseCase(chapter)) {
                is QuizResult.Success -> {
                    val data = result.result
                    val domains = data.map { it.toDomain() }
                    _quizContents.update { domains }
                    _quizContents.value.forEach { content ->
                        launch {
                            val soundUri = soundUriUseCase(content.soundName)
                            _quizContents.update { current ->
                                current.map { if (it.id == content.id) it.copy(soundUri = soundUri) else it }
                            }
                        }
                    }
                }

                is QuizResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }

    fun updateQuizContent(quizContentState: QuizContentState) {
        viewModelScope.launch {
            _quizContents.update { it.map { if (it.id == quizContentState.id) quizContentState else it } }
        }
    }

    fun soundPlay(uri: Uri?) {
        if (uri != null) {
            viewModelScope.launch {
                loadingDialogManager.show()
                audioPlayUseCase.playAudio(uri)
                loadingDialogManager.dismiss()
            }
        }
    }
}