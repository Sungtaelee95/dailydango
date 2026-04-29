package com.bhst.dailydango.data.quiz.di

import com.bhst.dailydango.data.quiz.QuizChapterLimitRepositoryImpl
import com.bhst.dailydango.data.quiz.QuizChapterRepositoryImpl
import com.bhst.dailydango.data.quiz.QuizRepositoryImpl
import com.bhst.dailydango.domain.repository.quiz.QuizChapterLimitRepository
import com.bhst.dailydango.domain.repository.quiz.QuizChapterRepository
import com.bhst.dailydango.domain.repository.quiz.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class QuizChapterRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideQuizChapterRepository(
        quizChapterRepositoryImpl: QuizChapterRepositoryImpl
    ): QuizChapterRepository

    @Binds
    @Singleton
    abstract fun provideQuizChapterLimitRepository(
        quizChapterLimitRepositoryImpl: QuizChapterLimitRepositoryImpl
    ): QuizChapterLimitRepository

    @Binds
    @Singleton
    abstract fun provideQuizRepository(
        quizRepositoryImpl: QuizRepositoryImpl
    ): QuizRepository
}