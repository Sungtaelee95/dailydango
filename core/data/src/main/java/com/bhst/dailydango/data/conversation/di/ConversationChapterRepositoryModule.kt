package com.bhst.dailydango.data.conversation.di

import com.bhst.dailydango.data.chapter.ChapterLimitRepositoryImpl
import com.bhst.dailydango.data.chapter.ChapterRepositoryImpl
import com.bhst.dailydango.data.conversation.ConversationChapterLimitRepositoryImpl
import com.bhst.dailydango.data.conversation.ConversationChapterRepositoryImpl
import com.bhst.dailydango.data.conversation.ConversationRepositoryImpl
import com.bhst.dailydango.domain.repository.chapter.ChapterLimitRepository
import com.bhst.dailydango.domain.repository.chapter.ChapterRepository
import com.bhst.dailydango.domain.repository.conversation.ConversationChapterLimitRepository
import com.bhst.dailydango.domain.repository.conversation.ConversationChapterRepository
import com.bhst.dailydango.domain.repository.conversation.ConversationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class ConversationChapterRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideConversationChapterRepository(
        conversationChapterRepositoryImpl: ConversationChapterRepositoryImpl
    ): ConversationChapterRepository

    @Binds
    @Singleton
    abstract fun provideConversationChapterLimitRepository(
        conversationChapterLimitRepositoryImpl: ConversationChapterLimitRepositoryImpl
    ): ConversationChapterLimitRepository

    @Binds
    @Singleton
    abstract fun provideConversationRepository(
        conversationRepositoryImpl: ConversationRepositoryImpl
    ): ConversationRepository
}