package com.bhst.dailydango.data.tip.di

import com.bhst.dailydango.data.tip.TipRepositoryImpl
import com.bhst.dailydango.domain.repository.tip.TipRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class TipRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTipRepository(
        tipRepositoryImpl: TipRepositoryImpl
    ): TipRepository
}