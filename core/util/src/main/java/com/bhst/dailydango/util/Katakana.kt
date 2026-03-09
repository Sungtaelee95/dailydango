package com.bhst.dailydango.util


object KatakanaData {

    // 1. 기본 (청음) - 5칸 기준
    val basicMap = mapOf(
        "" to listOf("ア열", "イ열", "ウ열", "エ열", "オ열"),
        "ア행" to listOf("ア", "イ", "ウ", "エ", "オ"),
        "カ행" to listOf("カ", "キ", "ク", "ケ", "コ"),
        "サ행" to listOf("サ", "シ", "ス", "セ", "ソ"),
        "タ행" to listOf("タ", "チ", "ツ", "テ", "ト"),
        "ナ행" to listOf("ナ", "ニ", "ヌ", "ネ", "ノ"),
        "ハ행" to listOf("ハ", "ヒ", "フ", "ヘ", "ホ"),
        "マ행" to listOf("マ", "ミ", "ム", "メ", "モ"),
        "ヤ행" to listOf("ヤ", "", "ユ", "", "ヨ"),
        "ラ행" to listOf("ラ", "リ", "ル", "レ", "ロ"),
        "ワ행" to listOf("ワ", "", "", "", "ヲ"),
        "ン행" to listOf("", "", "ン", "", "")
    )

    // 2. 탁음/반탁음 - 5칸 기준
    val dakuonMap = mapOf(
        "" to listOf("ア열", "イ열", "ウ열", "エ열", "オ열"),
        "ガ행" to listOf("ガ", "ギ", "グ", "ゲ", "ゴ"),
        "ザ행" to listOf("ザ", "ジ", "ズ", "ゼ", "ゾ"),
        "ダ행" to listOf("ダ", "ヂ", "ヅ", "デ", "ド"),
        "バ행" to listOf("バ", "ビ", "ブ", "ベ", "ボ"),
        "パ행" to listOf("パ", "ピ", "プ", "ペ", "ポ")
    )

    // 3. 요음 - 3칸 기준 (~ヤ, ~ユ, ~ヨ)
    val yoonMap = mapOf(
        "" to listOf("~ヤ", "~ユ", "~ヨ"),
        "カ행" to listOf("キャ", "キュ", "キョ"),
        "ガ행" to listOf("ギャ", "ギュ", "ギョ"),
        "サ행" to listOf("シャ", "シュ", "ショ"),
        "ザ행" to listOf("ジャ", "ジュ", "ジョ"),
        "タ행" to listOf("チャ", "チュ", "チョ"),
        "ナ행" to listOf("ニャ", "ニュ", "ニョ"),
        "ハ행" to listOf("ヒャ", "ヒュ", "ヒョ"),
        "バ행" to listOf("ビャ", "ビュ", "ビョ"),
        "パ행" to listOf("ピャ", "ピュ", "ピョ"),
        "マ행" to listOf("ミャ", "ミュ", "ミョ"),
        "ラ행" to listOf("リャ", "リュ", "リョ")
    )

    // 4. 촉음 - 5칸 기준
    val sokuonMap = mapOf(
        "" to listOf("ア열", "イ열", "ウ열", "エ열", "オ열"),
        "タ행" to listOf("", "", "ッ", "", "")
    )
}