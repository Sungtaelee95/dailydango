package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.chapter.Chapter
import com.bhst.dailydango.model.error.FbError

sealed class ChapterResult {
    data class Success(val data: List<Chapter>) : ChapterResult()
    data class Error(val error: FbError) : ChapterResult()
}
