package com.bhst.dailydango.model.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bhst.dailydango.model.content.Content
import com.bhst.dailydango.model.content.ContentState

@Entity(tableName = "favorite_content")
data class FavoriteContentEntity(
    @PrimaryKey
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
    val exampleForJapanese3: String = "",
    val explanationForKorean3: String = "",
    val explanationForKoreanSound3: String = "",
    val exampleForJapanese4: String = "",
    val explanationForKorean4: String = "",
    val explanationForKoreanSound4: String = "",
    val order: Int = 1,
) {
    companion object {
        fun from(content: ContentState): FavoriteContentEntity {
            return FavoriteContentEntity(
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
                exampleForJapanese3 = content.exampleForJapanese3,
                explanationForKorean3 = content.explanationForKorean3,
                explanationForKoreanSound3 = content.explanationForKoreanSound3,
                exampleForJapanese4 = content.exampleForJapanese4,
                explanationForKorean4 = content.explanationForKorean4,
                explanationForKoreanSound4 = content.explanationForKoreanSound4,
                order = content.order,
            )
        }
    }

}
