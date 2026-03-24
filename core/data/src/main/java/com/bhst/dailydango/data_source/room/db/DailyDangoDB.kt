package com.bhst.dailydango.data_source.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bhst.dailydango.data_source.room.dao.FavoriteContentDao
import com.bhst.dailydango.data_source.room.dao.PlayRepeatDao
import com.bhst.dailydango.data_source.room.dao.PlaySpeedDao
import com.bhst.dailydango.data_source.room.dao.ThemeConfigDao
import com.bhst.dailydango.model.play_repeat.PlayRepeatEntity
import com.bhst.dailydango.model.play_speed.PlaySpeedEntity
import com.bhst.dailydango.model.room.entity.FavoriteContentEntity
import com.bhst.dailydango.model.theme.converter.ThemeConverter
import com.bhst.dailydango.model.theme.entity.ThemeEntity

@Database(
    entities = [
        FavoriteContentEntity::class,
        ThemeEntity::class,
        PlaySpeedEntity::class,
        PlayRepeatEntity::class
    ],
    version = 5,
)
@TypeConverters(ThemeConverter::class)
abstract class DailyDangoDB : RoomDatabase() {
    abstract fun favoriteContentDao(): FavoriteContentDao
    abstract fun themeConfigDao(): ThemeConfigDao
    abstract fun playSpeedDao(): PlaySpeedDao
    abstract fun playRepeatDao(): PlayRepeatDao
}