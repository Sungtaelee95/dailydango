package com.bhst.dailydango.data.word.di

import com.bhst.dailydango.data.word.WordRepositoryImpl
import com.bhst.dailydango.domain.repository.word.WordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class WordRepositoryModule {
    @Binds
    @Singleton
    abstract fun providesWordContentRepository(
        wordRepositoryImpl: WordRepositoryImpl
    ): WordRepository
}