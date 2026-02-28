package com.bhst.dailydango.ui

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageManager @Inject constructor(

) {
    private val _messages = Channel<String>(Channel.Factory.CONFLATED)
    val message = _messages.receiveAsFlow()

    suspend fun sendMessage(message: String) {
        _messages.send(message)
    }
}