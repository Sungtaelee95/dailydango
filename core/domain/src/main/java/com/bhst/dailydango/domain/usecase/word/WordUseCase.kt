package com.bhst.dailydango.domain.usecase.word

import com.bhst.dailydango.domain.repository.word.WordRepository
import javax.inject.Inject

class WordUseCase @Inject constructor(
    private val wordRepository: WordRepository
) {
    suspend operator fun invoke(word: String) = wordRepository.getWordContent(word)
}
