package com.bhst.dailydango.model.theme.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bhst.dailydango.model.theme.config.ThemeConfig

@Entity(tableName = "theme")
data class ThemeEntity(
    @PrimaryKey val id: Int = 1,
    val themeConfig: ThemeConfig = ThemeConfig.SYSTEM
)