package com.bhst.dailydango.domain.repository.sound_uri

import android.net.Uri

interface SoundUriRepository {
    suspend fun getSoundUri(fileName: String): Uri?
}