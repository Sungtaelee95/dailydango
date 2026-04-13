package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.chapter.ChapterLimit
import com.bhst.dailydango.model.error.FbError

sealed class ChapterLimitResult {
    data class Success(val data: ChapterLimit) : ChapterLimitResult()
    data class Error(val error: FbError) : ChapterLimitResult()
}