package com.bhst.dailydango.basic_expressions.word_study

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.basic.expressions.R
import com.bhst.dailydango.domain.usecase.favorite.DeleteFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.FavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.SetFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.domain.usecase.word_content.WordContentUseCase
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.content.ContentUri
import com.bhst.dailydango.model.result.WordContentResult
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordStudyViewModel @Inject constructor(
    private val wordContentUseCase: WordContentUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val setFavoriteUseCase: SetFavoritesContentUseCase,
    private val favoriteContentsUseCase: FavoritesContentUseCase,
    private val deleteFavoriteUseCase: DeleteFavoritesContentUseCase,
    @param: ApplicationContext private val context: Context
): ViewModel() {
    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    private var favoriteJob: Job? = null

    override fun onCleared() {
        super.onCleared()
        favoriteJob = null
        loadingDialogManager.dismiss()
    }
    fun getWordContent(chapter: Int) {
        viewModelScope.launch {
            loadingDialogManager.show()
            when (val result = wordContentUseCase(chapter = chapter)) {
                is WordContentResult.Success -> {
                    // 1. 텍스트 데이터를 먼저 화면에 보여주기 위해 상태 방출
                    val initialContents = result.contents.map { ContentState.from(content = it) }
                    _uiState.emit(initialContents)
                    getFavoriteContents()
                    // 2. 이후 백그라운드에서 개별 항목의 오디오 URI를 가져와 업데이트
                    initialContents.forEach { content ->
                        launch {
                            val contentUri = ContentUri(
                                titleSoundUri = soundUriUseCase(content.japaneseTitle),
                                explanationSoundUri1 = soundUriUseCase(content.exampleForJapanese1),
                                explanationSoundUri2 = soundUriUseCase(content.exampleForJapanese2),
                                explanationSoundUri3 = soundUriUseCase(content.exampleForJapanese3),
                                explanationSoundUri4 = soundUriUseCase(content.exampleForJapanese4),
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

                is WordContentResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_message))
                }
            }
            loadingDialogManager.dismiss()
        }
    }

    fun updateSentenceContent(content: ContentState) {
        viewModelScope.launch {
            loadingDialogManager.show()
            if (content.isBookmark) {
                setFavoriteUseCase(content)
            } else {
                deleteFavoriteUseCase(content.id)
            }
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

    private fun getFavoriteContents() {
        favoriteJob?.cancel()
        favoriteJob = viewModelScope.launch {
            favoriteContentsUseCase().collect { favoriteList ->
                // 1. 즐겨찾기 목록을 Set으로 변환하여 O(1) 검색 속도 확보
                val favoriteTitles = favoriteList.map { it.japaneseTitle }.toSet()

                // 2. 루프 밖에서 update를 한 번만 호출하여 UI 리렌더링 최소화
                _uiState.update { currentList ->
                    currentList.map { item ->
                        val isBookmark = favoriteTitles.contains(item.japaneseTitle)
                        // 상태가 변경되었을 때만 새 객체를 생성(copy)하여 메모리 낭비 방지
                        if (item.isBookmark != isBookmark) {
                            item.copy(isBookmark = isBookmark)
                        } else {
                            item
                        }
                    }
                }
            }
        }
    }
}