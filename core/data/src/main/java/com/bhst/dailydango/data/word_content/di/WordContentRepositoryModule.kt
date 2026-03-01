package com.bhst.dailydango.data.word_content.di

import com.bhst.dailydango.data.word_content.WordContentRepositoryImpl
import com.bhst.dailydango.domain.repository.word_content.WordContentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class  WordContentRepositoryModule {
    @Binds
    @Singleton
    abstract fun providesWordContentRepository(
        wordContentRepositoryImpl: WordContentRepositoryImpl
    ): WordContentRepository

}