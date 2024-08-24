package com.example.noteify.RoomDB

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*
@ProvidedTypeConverter
class RouteConverter{
    @TypeConverter
    fun fromRoute(route: Route ): String {
        return Json.encodeToString(route)
    }

    @TypeConverter
    fun toRoute(route : String): Route {
        return Json.decodeFromString(route)
    }
}


@ProvidedTypeConverter
class DrawLinesListConverter {
    @TypeConverter
    fun fromDrawLinesList(drawLines:  MutableList<DrawLines>): String {
    return Json.encodeToString(drawLines)
    }

    @TypeConverter
    fun toDrawLinesList(value: String): MutableList<DrawLines>{
        return Json.decodeFromString(value)
    }
}
@ProvidedTypeConverter
class DrawLineConverter {
    @TypeConverter
    fun fromDrawLine(drawLine: DrawLines): String {
        return Json.encodeToString(drawLine)
    }
    @TypeConverter
    fun toDrawLine(value: String): DrawLines {
        return Json.decodeFromString(value)
    }
}


@ProvidedTypeConverter
class PairConverter{

    @TypeConverter
    fun fromPair(pair :Pair<Float,Float>):String{
        return Json.encodeToString(pair)
    }

    @TypeConverter
    fun toPair(value:String): Pair<Float,Float>{
        return Json.decodeFromString(value)
    }
}
@ProvidedTypeConverter
class PairListConverter {
    @TypeConverter
    fun fromPairList(pairList: MutableList<Pair<Float, Float>>): String {
        return Json.encodeToString(pairList)
    }

    @TypeConverter
    fun toPairList(value: String): MutableList<Pair<Float, Float>> {
        return Json.decodeFromString(value)
    }
}   */

@ProvidedTypeConverter
class DataConverters{
    @TypeConverter
    fun fromPairList(pairList: MutableList<Pair<Float, Float>>): String {
        return Json.encodeToString(pairList)
    }

    @TypeConverter
    fun toPairList(value: String): MutableList<Pair<Float, Float>> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromPair(pair :Pair<Float,Float>):String{
        return Json.encodeToString(pair)
    }

    @TypeConverter
    fun toPair(value:String): Pair<Float,Float>{
        return Json.decodeFromString(value)
    }
    @TypeConverter
    fun fromDrawLine(drawLine: DrawLines): String {
        return Json.encodeToString(drawLine)
    }
    @TypeConverter
    fun toDrawLine(value: String): DrawLines {
        return Json.decodeFromString(value)
    }
    @TypeConverter
    fun fromDrawLinesList(drawLines:  MutableList<DrawLines?>): String {
        return Json.encodeToString(drawLines)
    }

    @TypeConverter
    fun toDrawLinesList(value: String ): MutableList<DrawLines?>{
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromRoute(route: Route ): String {
        return Json.encodeToString(route)
    }

    @TypeConverter
    fun toRoute(route : String): Route {
        return Json.decodeFromString(route)
    }
}

