package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.hanja.HanjaContent

sealed class HanjaResult {
    data class Success(val content: HanjaContent?) : HanjaResult()
    data class Error(val error: FbError) : HanjaResult()
}