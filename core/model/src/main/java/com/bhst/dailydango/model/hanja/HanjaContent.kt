package com.bhst.dailydango.model.hanja

import kotlinx.serialization.Serializable

@Serializable
data class HanjaContent(
    val hanja: String = "",
    val koreanMeaning: String = "", // 한국어 훈 (예: "먼저")
    val koreanSound: String = "",   // 한국어 음 (예: "선")
    val koreanMeaning2: String = "", // 한국어 훈 (예: "먼저")
    val koreanSound2: String = "",   // 한국어 음 (예: "선")
    val koreanMeaning3: String = "", // 한국어 훈 (예: "먼저")
    val koreanSound3: String = "",   // 한국어 음 (예: "선")
    val koreanMeaning4: String = "", // 한국어 훈 (예: "먼저")
    val koreanSound4: String = "",   // 한국어 음 (예: "선")
    val jlptLevel: String = "",     // JLPT 급수 (예: JLPT N4)
    val grade: String = "",         // (예: "小1")
    val onyomi: String = "",        // 음독 (예: "せん")
    val kunyomi: String = "",       // 훈독 (예: "さき")
    val radical: String = "",       // 부수 (예: "儿")
    val strokeCount: String = ""    // 획수 (예: "2")
)