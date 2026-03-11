package com.bhst.dailydango.data.gif.di

import com.bhst.dailydango.data.gif.GifRepositoryImpl
import com.bhst.dailydango.domain.repository.gif.GifRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract  class GifRepositoryModule {
    @Binds
    @Singleton
    abstract fun providesGifRepository(
        gifRepositoryImpl: GifRepositoryImpl
    ): GifRepository

}