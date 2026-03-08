package com.bhst.dailydango.model.theme.converter

import androidx.room.TypeConverter
import com.bhst.dailydango.model.theme.config.ThemeConfig

class ThemeConverter {
    @TypeConverter
    fun fromThemeConfig(themeConfig: ThemeConfig): String {
        return themeConfig.name
    }
    @TypeConverter
    fun toThemeConfig(value: String): ThemeConfig {
        return try {
            enumValueOf<ThemeConfig>(value)
        } catch (e: IllegalArgumentException) {
            ThemeConfig.SYSTEM // 예외 발생 시 기본값 폴백
        }
    }
}