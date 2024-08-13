package com.example.noteify.RoomDB

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity
data class Route(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @TypeConverters(DrawLinesListConverter::class)
    var path: MutableList <DrawLines>,
)

@Entity
data class DrawLines(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @TypeConverters(OffsetListConverter::class)
    var path: MutableList<Offset>,
    var color: Long,
    var strokeWidth: Float,
    var alpha: Float
){
    init {
        id = 0
        color = 0xFFFFFFFF
        strokeWidth = 0f
        alpha = 0f
    }
}
