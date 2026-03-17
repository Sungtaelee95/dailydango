package com.bhst.dailydango.search.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.favorite.DeleteFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.SetFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sentence.SentenceUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.domain.usecase.word_content.WordContentUseCase
import com.bhst.dailydango.model.content.ContentState
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
class SearchViewModel @Inject constructor(
    private val sentenceUseCase: SentenceUseCase,
    private val wordContentUseCase: WordContentUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val setFavoriteUseCase: SetFavoritesContentUseCase,
    private val deleteFavoriteUseCase: DeleteFavoritesContentUseCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun getAllContent() {
        viewModelScope.launch {

            loadingDialogManager.show()

            val accumulatedList = mutableListOf<ContentState>()

            for (chapter in 1..99) {
                when (val result = wordContentUseCase(chapter = chapter)) {
                    is WordContentResult.Success -> {
                        accumulatedList.addAll(result.contents.map { ContentState.from(content = it) })
                    }

                    is WordContentResult.Error -> messageManager.sendMessage("서버 에러 발생")
                }

                when (val result = sentenceUseCase(chapter = chapter)) {
                    is SentenceContentResult.Success -> {
                        accumulatedList.addAll(result.contents.map { ContentState.from(content = it) })
                    }

                    is SentenceContentResult.Error -> messageManager.sendMessage("서버 에러 발생")
                }

                // 챕터 10개마다 한 번씩 UI 업데이트 (UX 개선 및 오버헤드 감소)
                if (chapter % 10 == 0 || chapter == 99) {
                    _uiState.update { accumulatedList.toList() }
                }
            }
            loadingDialogManager.dismiss()
        }

    }

    // 🚨 오디오 URI는 클릭 시점에만 가져오도록 최적화 (Lazy Loading)
    fun playSoundFor(japaneseText: String) {
        viewModelScope.launch {
            loadingDialogManager.show()
            try {
                // 클릭한 항목의 텍스트로 URI를 실시간으로 가져옴
                val uri = soundUriUseCase(japaneseText)
                uri?.let { audioPlayUseCase.playAudio(uri = it) }
            } catch (e: Exception) {
                messageManager.sendMessage("오디오를 불러올 수 없습니다.")
            } finally {
                loadingDialogManager.dismiss()
            }
        }
    }

    fun updateSearchContent(content: ContentState) {
        viewModelScope.launch {
            loadingDialogManager.show()
            if (content.isBookmark) {
                setFavoriteUseCase(content)
            } else {
                deleteFavoriteUseCase(content.japaneseTitle)
            }
            _uiState.update { currentList ->
                currentList.map {
                    if (it.uuid == content.uuid) {
                        content // 누른 아이템만 업데이트된 상태로 교체
                    } else {
                        it
                    }
                }
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