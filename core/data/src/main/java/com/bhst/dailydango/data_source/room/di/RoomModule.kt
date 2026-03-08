package com.bhst.dailydango.data_source.room.di

import android.content.Context
import androidx.room.Room
import com.bhst.dailydango.data_source.room.dao.FavoriteContentDao
import com.bhst.dailydango.data_source.room.dao.PlaySpeedDao
import com.bhst.dailydango.data_source.room.dao.ThemeConfigDao
import com.bhst.dailydango.data_source.room.db.DailyDangoDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Provides
    @Singleton
    fun providesDailyDangoDB(
        @ApplicationContext context: Context
    ): DailyDangoDB {
        return Room.databaseBuilder(
            context,
            DailyDangoDB::class.java,
            "daily_dango_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun providesFavoriteContentDao(
        dailyDangoDB: DailyDangoDB
    ): FavoriteContentDao {
        return dailyDangoDB.favoriteContentDao()
    }

    @Provides
    @Singleton
    fun providesThemeConfigDao(
        dailyDangoDB: DailyDangoDB
    ): ThemeConfigDao {
        return dailyDangoDB.themeConfigDao()
    }

    @Provides
    @Singleton
    fun providesPlaySpeedDao(
        dailyDangoDB: DailyDangoDB
    ): PlaySpeedDao {
        return dailyDangoDB.playSpeedDao()
    }
}