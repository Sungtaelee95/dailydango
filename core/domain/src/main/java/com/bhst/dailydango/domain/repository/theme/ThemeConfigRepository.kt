package com.bhst.dailydango.domain.repository.theme

import com.bhst.dailydango.model.theme.config.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface ThemeConfigRepository {
    fun getAppThemeConfig(): Flow<ThemeConfig>
    suspend fun updateAppThemeConfig(themeConfig: ThemeConfig)
}
