package com.bhst.dailydango.data.image.di

import com.bhst.dailydango.data.image.UploadSuggestionImageRepositoryImpl
import com.bhst.dailydango.domain.repository.image.UploadSuggestionImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class UploadSuggestionImageRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideUploadImageRepository(
        uploadSuggestionImageRepositoryImpl: UploadSuggestionImageRepositoryImpl
    ): UploadSuggestionImageRepository
}