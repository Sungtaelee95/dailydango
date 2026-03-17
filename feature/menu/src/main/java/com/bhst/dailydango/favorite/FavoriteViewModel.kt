package com.bhst.dailydango.favorite

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.favorite.DeleteFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.FavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.content.ContentUri
import com.bhst.dailydango.ui.LoadingDialogManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoritesContentUseCase,
    private val soundUriUseCase: SoundUriUseCase,
    private val deleteFavoriteUseCase: DeleteFavoritesContentUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val loadingDialogManager: LoadingDialogManager,
): ViewModel() {
    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun getFavoriteContents() {
        viewModelScope.launch {
            favoriteUseCase().collect { favoriteContents ->
                loadingDialogManager.show()
                _uiState.update { favoriteContents }
                favoriteContents.forEach { content ->
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
                                if (it.japaneseTitle == content.japaneseTitle) {
                                    it.copy(contentUri = contentUri)
                                } else {
                                    it
                                }
                            }
                        }
                    }
                }
                loadingDialogManager.dismiss()
            }
        }
    }

    fun updateFavoriteContent(contentState: ContentState) {
        viewModelScope.launch {
            loadingDialogManager.show()
            if (!contentState.isBookmark) {
                deleteFavoriteUseCase(contentState.japaneseTitle)
            } else {
                _uiState.update { currentList ->
                    currentList.map {
                        if (it.japaneseTitle == contentState.japaneseTitle) {
                            contentState // 누른 아이템만 업데이트된 상태로 교체
                        } else {
                            it
                        }
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
}