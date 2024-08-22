package com.example.noteify.RoomDB


import androidx.compose.ui.geometry.Offset
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Entity
@Serializable
data class Route(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var path: MutableList <DrawLines?>,
)

@Entity
@Serializable
data class DrawLines(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var path: MutableList< @Serializable Pair<Float, Float> >,
    var color: Long = 0xFFFFFFFF,
    var strokeWidth: Float = 5f,
    var alpha: Float = 1f
)



var defaultRoute: Route = Route(0, mutableListOf<DrawLines?>(DrawLines(0, mutableListOf<Pair<Float, Float>>(Pair(0f,0f)),0xFFFFFFFF,5f,1f)))