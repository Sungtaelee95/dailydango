package com.bhst.dailydango.domain.usecase.sentence

import com.bhst.dailydango.domain.repository.sentence_content.SentenceContentRepository
import javax.inject.Inject

class SentenceUseCase @Inject constructor(
    private val sentenceContentRepository: SentenceContentRepository
){
    suspend operator fun invoke(chapter: Int) = sentenceContentRepository.getSentenceContent(chapter)
}