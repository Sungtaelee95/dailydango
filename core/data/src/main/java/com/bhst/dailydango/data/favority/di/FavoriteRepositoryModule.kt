package com.bhst.dailydango.data.favority.di

import com.bhst.dailydango.data.favority.FavoriteRepositoryImpl
import com.bhst.dailydango.domain.repository.favorite.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class FavoriteRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository
}