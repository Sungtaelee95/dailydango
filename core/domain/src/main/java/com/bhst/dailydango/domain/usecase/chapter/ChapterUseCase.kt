package com.bhst.dailydango.domain.usecase.chapter

import com.bhst.dailydango.domain.repository.chapter.ChapterRepository
import javax.inject.Inject

class ChapterUseCase @Inject constructor(
    private val chapterRepository: ChapterRepository
) {
    suspend operator fun invoke() = chapterRepository.getChapters()
}