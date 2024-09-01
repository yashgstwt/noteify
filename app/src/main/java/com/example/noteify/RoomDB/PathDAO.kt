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

    @Upsert
    suspend fun insertDrawLine(drawLine:DrawLines)

    @Query("DELETE FROM Route WHERE id = :routeId")
    suspend fun deleteRouteById(routeId: Int)

    @Query("Select * from Route ")
    fun getAllPaths(): Flow<List<Route>>

}