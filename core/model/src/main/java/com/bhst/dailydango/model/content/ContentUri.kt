package com.bhst.dailydango.model.content

import android.net.Uri

data class ContentUri(
    val titleSoundUri: Uri? = null,
    val explanationSoundUri1: Uri? = null,
    val explanationSoundUri2: Uri? = null,
    val explanationSoundUri3: Uri? = null,
    val explanationSoundUri4: Uri? = null
) {
    fun isLoading(): Boolean {
        return titleSoundUri != null &&
                explanationSoundUri1 != null &&
                explanationSoundUri2 != null &&
                explanationSoundUri3 != null &&
                explanationSoundUri4 != null
    }
}
