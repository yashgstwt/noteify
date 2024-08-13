package com.example.noteify.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface PathDAO {
    @Upsert
    suspend fun insert(line : Route)

    @Delete
    suspend fun delete(line : Route)

    @Query("Select * from Route ")
    fun getAllPaths(): Flow<List<Route>>

}