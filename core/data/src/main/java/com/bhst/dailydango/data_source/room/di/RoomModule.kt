package com.bhst.dailydango.data_source.room.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // 1. 새로운 구조의 임시 테이블 생성 (id를 PK로 지정)
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

            // 2. 기존 데이터를 새 테이블로 복사 (수정된 부분: INSERT OR REPLACE INTO 적용)
            // 중복된 id가 발생할 경우 기존 데이터를 무시하고 덮어씁니다.
            database.execSQL(
                "INSERT OR REPLACE INTO favorite_content_new (id, titleHanja, japaneseTitle, japaneseTitleOfSoundToKorea, partOfSpeech, titleToKorean, tip, exampleForJapanese1, explanationForKorean1, explanationForKoreanSound1, exampleForJapanese2, explanationForKorean2, explanationForKoreanSound2, exampleForJapanese3, explanationForKorean3, explanationForKoreanSound3, exampleForJapanese4, explanationForKorean4, explanationForKoreanSound4, `order`) " +
                        "SELECT id, titleHanja, japaneseTitle, japaneseTitleOfSoundToKorea, partOfSpeech, titleToKorean, tip, exampleForJapanese1, explanationForKorean1, explanationForKoreanSound1, exampleForJapanese2, explanationForKorean2, explanationForKoreanSound2, exampleForJapanese3, explanationForKorean3, explanationForKoreanSound3, exampleForJapanese4, explanationForKorean4, explanationForKoreanSound4, `order` FROM favorite_content"
            )

            // 3. 기존 테이블 삭제
            database.execSQL("DROP TABLE favorite_content")

            // 4. 임시 테이블의 이름을 원래 이름으로 변경
            database.execSQL("ALTER TABLE favorite_content_new RENAME TO favorite_content")
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