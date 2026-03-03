package com.bhst.dailydango.data.player.di

import com.bhst.dailydango.data.player.PlayAudioRepositoryImpl
import com.bhst.dailydango.domain.repository.player.PlayAudioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class PlayAudioRepositoryModule {
    @Binds
    @Singleton
    abstract fun providePlayAudioRepository(
        playAudioRepositoryImpl: PlayAudioRepositoryImpl
    ): PlayAudioRepository

}