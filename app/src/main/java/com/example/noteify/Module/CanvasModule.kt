package com.example.noteify.Module

import android.app.Application
import androidx.room.Room
import com.example.noteify.Repository.canvasRepository
import com.example.noteify.RoomDB.NoteifyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CanvasModule {

    @Provides
    @Singleton
    fun provideDataBase (app :Application ) : NoteifyDB {
        return Room.databaseBuilder(app , NoteifyDB::class.java , "NoteifyDB").build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: NoteifyDB  ) : canvasRepository{
        return canvasRepository(db.dao)
    }

}