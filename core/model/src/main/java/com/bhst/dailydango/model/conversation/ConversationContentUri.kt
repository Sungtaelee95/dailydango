package com.bhst.dailydango.model.conversation

import android.net.Uri

data class ConversationContentUri(
    val explanationSoundUri1: Uri? = null,
    val explanationSoundUri2: Uri? = null,
    val explanationSoundUri3: Uri? = null,
    val explanationSoundUri4: Uri? = null,
    val explanationSoundUri5: Uri? = null,
    val explanationSoundUri6: Uri? = null,
    val explanationSoundUri7: Uri? = null,
    val explanationSoundUri8: Uri? = null,
    val explanationSoundUri9: Uri? = null,
    val explanationSoundUri10: Uri? = null
) {
    fun getContentUriList(): List<Uri> = listOfNotNull(
        explanationSoundUri1,
        explanationSoundUri2,
        explanationSoundUri3,
        explanationSoundUri4,
        explanationSoundUri5,
        explanationSoundUri6,
        explanationSoundUri7,
        explanationSoundUri8,
        explanationSoundUri9,
        explanationSoundUri10
    )
}