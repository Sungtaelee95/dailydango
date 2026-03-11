package com.bhst.dailydango.domain.repository.gif

import android.net.Uri

interface GifRepository {
    suspend fun getGifUri(fileName: String): Uri?
}