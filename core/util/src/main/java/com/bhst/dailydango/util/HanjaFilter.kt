package com.bhst.dailydango.util

fun String.filterHanja(): List<String> {
    // 한자의 유니코드 범위:
    // \u4E00-\u9FFF : CJK 통합 한자 (가장 보편적인 한자)
    // \uF900-\uFAFF : CJK 호환용 한자
    val hanjaRegex = Regex("[\\u4E00-\\u9FFF\\uF900-\\uFAFF]")

    return hanjaRegex.findAll(this)
        .map { it.value }
        .toList()
        .distinct()
}