package com.bhst.dailydango.data.lock.di

import com.bhst.dailydango.data.lock.LockRepositoryImpl
import com.bhst.dailydango.domain.repository.lock.LockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class LockRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideLockRepository(
        lockRepositoryImpl: LockRepositoryImpl
    ): LockRepository
}