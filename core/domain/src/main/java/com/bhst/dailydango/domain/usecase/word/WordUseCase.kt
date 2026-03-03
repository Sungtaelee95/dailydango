package com.bhst.dailydango.domain.usecase.word

import com.bhst.dailydango.domain.repository.word_content.WordContentRepository
import javax.inject.Inject

class WordUseCase @Inject constructor(
    private val wordContentRepository: WordContentRepository
) {
    suspend operator fun invoke(chapter: Int) = wordContentRepository.getWordContent(chapter)
}