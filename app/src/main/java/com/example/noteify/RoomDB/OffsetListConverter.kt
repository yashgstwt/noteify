package com.example.noteify.RoomDB

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class OffsetListConverter {
    @TypeConverter
    fun fromOffsetList(offsets: MutableList<Offset>): String {

        return Json.encodeToString(offsets)
    }

    @TypeConverter
    fun toOffsetList(value: String): MutableList<Offset> {
        return Json.decodeFromString(value)
    }
}

class DrawLinesListConverter{

    @TypeConverter
    fun fromDrawLinesList(drawLines:  MutableList<DrawLines>): String {
    return Json.encodeToString(drawLines)
    }

    @TypeConverter
    fun toDrawLinesList(value: String): MutableList<DrawLines>{
        return Json.decodeFromString(value)
    }

}