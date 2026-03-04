package com.bhst.dailydango.data.sound_uri.di

import com.bhst.dailydango.data.sound_uri.SoundUriRepositoryImpl
import com.bhst.dailydango.domain.repository.sound_uri.SoundUriRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class SoundUriRepositoryModule {

    @Binds
    @Singleton
    abstract fun providesSoundUriRepository(
        soundUriRepositoryImpl: SoundUriRepositoryImpl
    ): SoundUriRepository
}