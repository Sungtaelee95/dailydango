package com.bhst.dailydango.data.hanja.di

import com.bhst.dailydango.data.hanja.HanjaRepositoryImpl
import com.bhst.dailydango.domain.repository.hanja.HanjaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class HanjaRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideHanjaRepository(
        hanjaRepositoryImpl: HanjaRepositoryImpl
    ): HanjaRepository

}