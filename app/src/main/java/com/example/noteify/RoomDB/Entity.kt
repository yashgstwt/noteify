package com.example.noteify.RoomDB


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity
@Serializable
data class Route(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var path: String? = null ,
    var name : String = "",
    var text : String = ""
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

data class TempRoute(
    val id: Int = 0 ,
    var path : List<List<DrawLines?>>,
    var name : String = "",
    var text : String = ""
)

