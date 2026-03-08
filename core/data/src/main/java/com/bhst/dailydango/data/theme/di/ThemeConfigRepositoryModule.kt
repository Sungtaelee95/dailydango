package com.bhst.dailydango.data.theme.di

import com.bhst.dailydango.data.theme.ThemeConfigRepositoryImpl
import com.bhst.dailydango.domain.repository.theme.ThemeConfigRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ThemeConfigRepositoryModule {
    @Binds
    @Singleton
    abstract fun providesThemeConfigRepository(
        themeConfigRepositoryImpl: ThemeConfigRepositoryImpl
    ): ThemeConfigRepository

}