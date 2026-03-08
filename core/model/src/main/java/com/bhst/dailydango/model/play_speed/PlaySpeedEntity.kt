package com.bhst.dailydango.model.play_speed

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "play_speed")
data class PlaySpeedEntity(
    @PrimaryKey
    val id: Int = 1,
    val speed: Float = 1.0f
)