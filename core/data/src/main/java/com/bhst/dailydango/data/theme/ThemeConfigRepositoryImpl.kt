package com.bhst.dailydango.data.theme

import com.bhst.dailydango.data_source.room.dao.ThemeConfigDao
import com.bhst.dailydango.domain.repository.theme.ThemeConfigRepository
import com.bhst.dailydango.model.theme.config.ThemeConfig
import com.bhst.dailydango.model.theme.entity.ThemeEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThemeConfigRepositoryImpl @Inject constructor(
    private val themeConfigDao: ThemeConfigDao
) : ThemeConfigRepository {
    override fun getAppThemeConfig(): Flow<ThemeConfig> {
        return themeConfigDao.getAppThemeConfig().map { it ?: ThemeConfig.SYSTEM }
    }

    override suspend fun updateAppThemeConfig(themeConfig: ThemeConfig) {
        withContext(IO) {
            val entity = ThemeEntity(id = 1, themeConfig = themeConfig)
            themeConfigDao.updateAppThemeConfig(entity)
        }

    }
}