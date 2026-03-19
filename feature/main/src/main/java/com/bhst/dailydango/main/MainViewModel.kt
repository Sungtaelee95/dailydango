package com.bhst.dailydango.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhst.dailydango.domain.usecase.favorite.DeleteFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.favorite.SetFavoritesContentUseCase
import com.bhst.dailydango.domain.usecase.player.AudioPlayUseCase
import com.bhst.dailydango.domain.usecase.sentence.SentenceUseCase
import com.bhst.dailydango.domain.usecase.sound_uri.SoundUriUseCase
import com.bhst.dailydango.domain.usecase.theme.ThemeConfigUseCase
import com.bhst.dailydango.domain.usecase.word_content.WordContentUseCase
import com.bhst.dailydango.model.content.ContentState
import com.bhst.dailydango.model.result.SentenceContentResult
import com.bhst.dailydango.model.result.WordContentResult
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeConfigUseCase: ThemeConfigUseCase,
    private val sentenceUseCase: SentenceUseCase,
    private val wordContentUseCase: WordContentUseCase,
    private val messageManager: MessageManager,
) : ViewModel() {

    val themeConfig = themeConfigUseCase()
    private val _allContents = MutableStateFlow<List<ContentState>>(emptyList())
    val allContents = _allContents.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _loadingProgress = MutableStateFlow(0f)
    val loadingProgress = _loadingProgress.asStateFlow()

    init {
        // 2️⃣ 뷰모델 생성 시 바로 데이터 로딩 시작
        getAllContent()
    }


    private fun getAllContent() {
        viewModelScope.launch {
            _isLoading.value = true
            val accumulatedList = mutableListOf<ContentState>()

            for (chapter in 1..MAX_CHAPTER) {
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

                _loadingProgress.value = chapter.toFloat() / MAX_CHAPTER.toFloat()

                // 챕터 10개마다 한 번씩 UI 업데이트 (UX 개선 및 오버헤드 감소)
                if (chapter == MAX_CHAPTER) {
                    _allContents.update { accumulatedList.toList() }
                    _isLoading.value = false
                }
            }
        }
    }

    companion object {
        private const val MAX_CHAPTER = 20
    }

}