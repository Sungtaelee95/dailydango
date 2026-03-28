package com.bhst.dailydango.data_source.room.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bhst.dailydango.data_source.room.dao.FavoriteContentDao
import com.bhst.dailydango.data_source.room.dao.PlayRepeatDao
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
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // (기존 코드 동일...)
            database.execSQL(
                "CREATE TABLE favorite_content_new (" +
                        "id TEXT NOT NULL PRIMARY KEY, " +
                        "titleHanja TEXT NOT NULL, " +
                        "japaneseTitle TEXT NOT NULL, " +
                        "japaneseTitleOfSoundToKorea TEXT NOT NULL, " +
                        "partOfSpeech TEXT NOT NULL, " +
                        "titleToKorean TEXT NOT NULL, " +
                        "tip TEXT NOT NULL, " +
                        "exampleForJapanese1 TEXT NOT NULL, " +
                        "explanationForKorean1 TEXT NOT NULL, " +
                        "explanationForKoreanSound1 TEXT NOT NULL, " +
                        "exampleForJapanese2 TEXT NOT NULL, " +
                        "explanationForKorean2 TEXT NOT NULL, " +
                        "explanationForKoreanSound2 TEXT NOT NULL, " +
                        "exampleForJapanese3 TEXT NOT NULL, " +
                        "explanationForKorean3 TEXT NOT NULL, " +
                        "explanationForKoreanSound3 TEXT NOT NULL, " +
                        "exampleForJapanese4 TEXT NOT NULL, " +
                        "explanationForKorean4 TEXT NOT NULL, " +
                        "explanationForKoreanSound4 TEXT NOT NULL, " +
                        "`order` INTEGER NOT NULL)"
            )
            database.execSQL(
                "INSERT OR REPLACE INTO favorite_content_new (id, titleHanja, japaneseTitle, japaneseTitleOfSoundToKorea, partOfSpeech, titleToKorean, tip, exampleForJapanese1, explanationForKorean1, explanationForKoreanSound1, exampleForJapanese2, explanationForKorean2, explanationForKoreanSound2, exampleForJapanese3, explanationForKorean3, explanationForKoreanSound3, exampleForJapanese4, explanationForKorean4, explanationForKoreanSound4, `order`) " +
                        "SELECT id, titleHanja, japaneseTitle, japaneseTitleOfSoundToKorea, partOfSpeech, titleToKorean, tip, exampleForJapanese1, explanationForKorean1, explanationForKoreanSound1, exampleForJapanese2, explanationForKorean2, explanationForKoreanSound2, exampleForJapanese3, explanationForKorean3, explanationForKoreanSound3, exampleForJapanese4, explanationForKorean4, explanationForKoreanSound4, `order` FROM favorite_content"
            )
            database.execSQL("DROP TABLE favorite_content")
            database.execSQL("ALTER TABLE favorite_content_new RENAME TO favorite_content")
        }
    }

    val MIGRATION_4_5 = object : Migration(4, 5) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL(
                """
            CREATE TABLE IF NOT EXISTS `play_repeat` (
                `id` INTEGER NOT NULL, 
                `repeat` INTEGER NOT NULL, 
                PRIMARY KEY(`id`)
            )
            """.trimIndent()
            )
        }
    }

    // 💡 새로운 마이그레이션 5 -> 6 (tipImages 컬럼 추가)
    val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // 빈 리스트가 Gson으로 직렬화되면 "[]" 이므로 기본값을 "[]"로 설정
            db.execSQL("ALTER TABLE `favorite_content` ADD COLUMN `tipImages` TEXT NOT NULL DEFAULT '[]'")
        }
    }

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
            .addMigrations(MIGRATION_3_4)
            .addMigrations(MIGRATION_4_5)
            .addMigrations(MIGRATION_5_6) // 💡 5 -> 6 마이그레이션 추가
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

    @Provides
    @Singleton
    fun providesPlayRepeatDao(
        dailyDangoDB: DailyDangoDB
    ): PlayRepeatDao {
        return dailyDangoDB.playRepeatDao()
    }
}