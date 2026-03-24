package com.bhst.dailydango.model.play_repeat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "play_repeat")
data class PlayRepeatEntity(
    @PrimaryKey
    val id: Int = 1,
    val repeat: Int = 1
)