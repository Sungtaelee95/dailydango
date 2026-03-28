package com.bhst.dailydango.model.room.converter

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {

    @TypeConverter
    fun fromStringList(value: List<String>?): String {
        // value가 null일 경우 빈 리스트 형태의 JSON 문자열("[]") 반환
        return if (value == null) {
            "[]"
        } else {
            Json.Default.encodeToString(value)
        }
    }

    @TypeConverter
    fun toStringList(value: String?): List<String> {
        return if (value.isNullOrEmpty()) {
            emptyList()
        } else {
            try {
                Json.Default.decodeFromString<List<String>>(value)
            } catch (e: Exception) {
                // 기존 데이터에 형식이 안 맞는 데이터가 있을 경우 앱 크래시 방지
                emptyList()
            }
        }
    }
}