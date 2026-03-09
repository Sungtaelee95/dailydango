package com.bhst.dailydango.util

import kotlin.collections.listOf

object HiraganaData {

    // 1. 기본 (청음) - 5칸 기준
    val basicMap = mapOf(
        "" to listOf("あ열", "お열", "い열", "う열", "え열"),
        "あ행" to listOf("あ", "い", "う", "え", "お"),
        "か행" to listOf("か", "き", "く", "け", "こ"),
        "さ행" to listOf("さ", "し", "す", "せ", "そ"),
        "た행" to listOf("た", "ち", "つ", "て", "と"),
        "な행" to listOf("な", "に", "ぬ", "ね", "の"),
        "は행" to listOf("は", "ひ", "ふ", "へ", "ほ"),
        "ま행" to listOf("ま", "み", "む", "め", "も"),
        "や행" to listOf("や", "", "ゆ", "", "よ"),
        "ら행" to listOf("ら", "り", "る", "れ", "ろ"),
        "わ행" to listOf("わ", "", "", "", "を"),
        "ん행" to listOf("", "", "ん", "", "")
    )

    // 2. 탁음/반탁음 - 5칸 기준
    val dakuonMap = mapOf(
        "" to listOf("あ열", "い열", "う열", "え열", "お열"),
        "が행" to listOf("が", "ぎ", "ぐ", "げ", "ご"),
        "ざ행" to listOf("ざ", "じ", "ず", "ぜ", "ぞ"),
        "だ행" to listOf("だ", "ぢ", "づ", "で", "ど"),
        "ば행" to listOf("ば", "び", "ぶ", "べ", "ぼ"),
        "ぱ행" to listOf("ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
    )

    // 3. 요음 - 3칸 기준 (~や, ~ゆ, ~よ)
    val yoonMap = mapOf(
        "" to listOf("~ゃ", "~ゅ", "~ょ"),
        "か행" to listOf("きゃ", "きゅ", "きょ"),
        "が행" to listOf("ぎゃ", "ぎゅ", "ぎょ"),
        "さ행" to listOf("しゃ", "しゅ", "しょ"),
        "ざ행" to listOf("じゃ", "じゅ", "じょ"),
        "た행" to listOf("ちゃ", "ちゅ", "ちょ"),
        "な행" to listOf("にゃ", "にゅ", "にょ"),
        "は행" to listOf("ひゃ", "ひゅ", "ひょ"),
        "ば행" to listOf("びゃ", "びゅ", "びょ"),
        "ぱ행" to listOf("ぴゃ", "ぴゅ", "ぴょ"),
        "ま행" to listOf("みゃ", "みゅ", "みょ"),
        "ら행" to listOf("りゃ", "りゅ", "りょ")
    )

    val sokuonMap = mapOf(
        "" to listOf("あ열","い열", "う열", "え열", "お열"),
        "た행" to listOf("","","っ","","")
    )
}