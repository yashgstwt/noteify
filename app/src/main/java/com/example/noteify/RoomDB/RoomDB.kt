package com.example.noteify.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DrawLines::class,Route::class ] , version = 2)
@TypeConverters(DataConverters::class)
abstract class NoteifyDB :RoomDatabase(){
    abstract val dao :PathDAO
}