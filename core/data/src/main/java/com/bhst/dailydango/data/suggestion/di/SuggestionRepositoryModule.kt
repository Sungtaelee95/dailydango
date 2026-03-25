package com.bhst.dailydango.data.suggestion.di

import com.bhst.dailydango.data.suggestion.SuggestionRepositoryImpl
import com.bhst.dailydango.domain.repository.suggestion.SuggestionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SuggestionRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideSuggestionRepository(
        suggestionRepositoryImpl: SuggestionRepositoryImpl
    ): SuggestionRepository
}