package com.bhst.dailydango.search.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.app.feature.search.R
import com.bhst.dailydango.domain.usecase.favorite.DeleteFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.FavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.SetFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.model.content.ContentState
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
class SearchViewModel @Inject constructor(
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    private val soundUriUseCase: SoundUriUseCase,
    private val audioPlayUseCase: AudioPlayUseCase,
    private val setFavoriteUseCase: SetFavoritesContentUseCase,
    private val deleteFavoriteUseCase: DeleteFavoritesContentUseCase,
    private val favoritesContentUseCase: FavoritesContentUseCase,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow<List<ContentState>>(emptyList())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        loadingDialogManager.dismiss()
    }

    fun setSearchContent(contents: List<ContentState>) {
        _uiState.value = contents
        getFavoritesContent()
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
                messageManager.sendMessage(context.getString(R.string.not_load_audio))
            } finally {
                loadingDialogManager.dismiss()
            }
        }
    }

    fun updateSearchContent(content: ContentState) {
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

    fun updateSearchFavorite(content: ContentState) {
        viewModelScope.launch {
            loadingDialogManager.show()
            if (content.isBookmark) {
                setFavoriteUseCase(content)
            } else {
                deleteFavoriteUseCase(content.id)
            }
            loadingDialogManager.dismiss()
        }
    }

    fun soundPlayRelease() {
        viewModelScope.launch {
            audioPlayUseCase.release()
        }
    }

    private fun getFavoritesContent() {
        viewModelScope.launch {
            favoritesContentUseCase().collect { favoriteContents ->
                // 💡 DB에서 가져온 즐겨찾기 항목들의 ID만 추출
                val favoriteIds = favoriteContents.map { it.id }.toSet()

                _uiState.update { currentList ->
                    currentList.map { content ->
                        // 객체 전체가 아닌 id를 기준으로 포함 여부 확인
                        val isFavorite = favoriteIds.contains(content.id)

                        // 현재 UI 상태와 DB 상태가 다를 때만 갱신
                        if (content.isBookmark != isFavorite) {
                            content.copy(isBookmark = isFavorite)
                        } else {
                            content
                        }
                    }
                }
            }
        }
    }
}