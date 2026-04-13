package com.bhst.dailydango.domain.usecase.chapter

import com.bhst.dailydango.domain.repository.chapter.ChapterLimitRepository
import javax.inject.Inject

class ChapterLimitUseCase @Inject constructor(
    private val chapterLimitRepository: ChapterLimitRepository
) {
    suspend operator fun invoke() = chapterLimitRepository.getChapterLimit()
}