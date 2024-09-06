package com.example.noteify.Module

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.noteify.Repository.canvasRepository
import com.example.noteify.RoomDB.DataConverters
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
        return Room
            .databaseBuilder(app , NoteifyDB::class.java , "NoteifyDB")
            .addTypeConverter( DataConverters())
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: NoteifyDB  ) : canvasRepository{
        return canvasRepository(db.dao)
    }

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // 1. Make 'path' nullable
        database.execSQL("ALTER TABLE Route RENAME TO Route_old")
        database.execSQL("CREATE TABLE Route (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, path TEXT, name TEXT NOT NULL DEFAULT '', text TEXT NOT NULL DEFAULT '')")
        database.execSQL("INSERT INTO Route (id, path, name, text) SELECT id, path, '', '' FROM Route_old")
        database.execSQL("DROP TABLE Route_old")

        // 2. Add 'name' and 'text' columns (this was already correct)
        // database.execSQL("ALTER TABLE Route ADD COLUMN name TEXT NOT NULL DEFAULT ''")
        // database.execSQL("ALTER TABLE Route ADD COLUMN text TEXT NOT NULL DEFAULT ''")
    }
}