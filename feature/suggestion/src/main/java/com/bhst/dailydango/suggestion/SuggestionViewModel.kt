package com.bhst.dailydango.suggestion

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.bhst.dailydango.app.feature.suggestion.R
import com.bhst.dailydango.domain.usecase.image.suggestion.SuggestionImageUploadUseCase
import com.bhst.dailydango.domain.usecase.suggestion.SuggestionUseCase
import com.bhst.dailydango.model.result.ImageResult
import com.bhst.dailydango.model.result.SuggestionResult
import com.bhst.dailydango.model.suggestion.SuggestionState
import com.bhst.dailydango.ui.LoadingDialogManager
import com.bhst.dailydango.ui.MessageManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SuggestionViewModel @Inject constructor(
    private val suggestionUseCase: SuggestionUseCase,
    private val suggestionImageUploadUseCase: SuggestionImageUploadUseCase,
    private val loadingDialogManager: LoadingDialogManager,
    private val messageManager: MessageManager,
    @param:ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(SuggestionState())
    val uiState = _uiState.asStateFlow()

    fun updateUiState(uiState: SuggestionState) {
        _uiState.update { uiState }
    }

    suspend fun uploadSuggestion() {
        if (!uiState.value.privacyConsentAt) return
        loadingDialogManager.show()
        val imagesUrls = mutableListOf<String>()
        uiState.value.attachmentUrls.forEach { uri ->
            when (val imageUrl = suggestionImageUploadUseCase(uri)) {
                is ImageResult.Success -> {
                    imagesUrls.add(imageUrl.imageUrl)
                }

                is ImageResult.Error -> {
                    messageManager.sendMessage(context.getString(R.string.server_error_msg))
                    loadingDialogManager.dismiss()
                    return
                }
            }
        }
        val suggestion = uiState.value.toData().copy(
            attachmentUrls = imagesUrls
        )
        when (val result = suggestionUseCase(suggestion)) {
            is SuggestionResult.Success -> {
                messageManager.sendMessage(result.message)
            }

            is SuggestionResult.Error -> {
                messageManager.sendMessage(context.getString(R.string.server_error_msg))
            }
        }
        loadingDialogManager.dismiss()
    }

}
