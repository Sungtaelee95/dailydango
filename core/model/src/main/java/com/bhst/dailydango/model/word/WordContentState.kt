package com.bhst.dailydango.model.word

import android.net.Uri

data class WordContentState(
    val word: String = "",
    val wordSound: String = "",
    val tip: String = "",
    val wordSoundUri: Uri? = null,
    val writeGifUri: Uri? = null,
    val isOpen: Boolean = false
) {
    companion object {
        fun from(wordContent: WordContent): WordContentState {
            return WordContentState(
                word = wordContent.word,
                wordSound = wordContent.wordSound,
                tip = wordContent.tip
            )
        }
    }
}
