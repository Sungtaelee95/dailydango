package com.bhst.dailydango.data.chapter.di

import com.bhst.dailydango.data.chapter.ChapterRepositoryImpl
import com.bhst.dailydango.domain.repository.chapter.ChapterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ChapterRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideChapterRepository(
        chapterRepositoryImpl: ChapterRepositoryImpl
    ): ChapterRepository
}