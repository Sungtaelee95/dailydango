package com.bhst.dailydango.model.content

import android.net.Uri

data class ContentState(
    val id: String = "", // 문서 ID 자동 매핑
    val titleHanja: String = "",
    val japaneseTitle: String = "",
    val japaneseTitleOfSoundToKorea: String = "",
    val partOfSpeech: String = "",
    val titleToKorean: String = "",
    val tip: String = "",
    val exampleForJapanese1: String = "",
    val explanationForKorean1: String = "",
    val explanationForKoreanSound1: String = "",
    val exampleForJapanese2: String = "",
    val explanationForKorean2: String = "",
    val explanationForKoreanSound2: String = "",
    val order: Int = 1, // 정렬을 위해 사용됩니다.
    val isOpen: Boolean = false,
    val isBookmark: Boolean = false,
    val contentUri: ContentUri = ContentUri()
) {
    companion object {
        fun from(content: Content): ContentState {
            return ContentState(
                id = content.id,
                titleHanja = content.titleHanja,
                japaneseTitle = content.japaneseTitle,
                japaneseTitleOfSoundToKorea = content.japaneseTitleOfSoundToKorea,
                partOfSpeech = content.partOfSpeech,
                titleToKorean = content.titleToKorean,
                tip = content.tip,
                exampleForJapanese1 = content.exampleForJapanese1,
                explanationForKorean1 = content.explanationForKorean1,
                explanationForKoreanSound1 = content.explanationForKoreanSound1,
                exampleForJapanese2 = content.exampleForJapanese2,
                explanationForKorean2 = content.explanationForKorean2,
                explanationForKoreanSound2 = content.explanationForKoreanSound2,
                order = content.order,
                isOpen = false,
                isBookmark = false,
            )
        }

        fun ContentState.toContent(): Content {
            return Content(
                id = this.id,
                titleHanja = this.titleHanja,
                japaneseTitle = this.japaneseTitle,
                japaneseTitleOfSoundToKorea = this.japaneseTitleOfSoundToKorea,
                partOfSpeech = this.partOfSpeech,
                titleToKorean = this.titleToKorean,
                tip = this.tip,
                exampleForJapanese1 = this.exampleForJapanese1,
                explanationForKorean1 = this.explanationForKorean1,
                explanationForKoreanSound1 = this.explanationForKoreanSound1,
                exampleForJapanese2 = this.exampleForJapanese2,
                explanationForKorean2 = this.explanationForKorean2,
                explanationForKoreanSound2 = this.explanationForKoreanSound2,
                order = this.order,
            )
        }
    }
}