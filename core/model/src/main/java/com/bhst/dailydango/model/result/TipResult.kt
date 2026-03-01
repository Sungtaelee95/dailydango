package com.bhst.dailydango.model.result

import com.bhst.dailydango.model.error.FbError
import com.bhst.dailydango.model.tip.Tip

sealed class TipResult {
    data class Success(val tipList: List<Tip>) : TipResult()
    data class Error(val error: FbError) : TipResult()
}