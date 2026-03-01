package com.bhst.dailydango.data.sentence_content.di

import com.bhst.dailydango.data.sentence_content.SentenceContentRepositoryImpl
import com.bhst.dailydango.domain.repository.sentence_content.SentenceContentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SentenceContentRepositoryModule {
    @Binds
    @Singleton
    abstract fun providesSentenceContentRepository(
        sentenceContentRepositoryImpl: SentenceContentRepositoryImpl
    ): SentenceContentRepository
}