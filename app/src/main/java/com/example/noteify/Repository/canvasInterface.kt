package com.example.noteify.Repository

import com.example.noteify.RoomDB.DrawLines
import com.example.noteify.RoomDB.Route
import kotlinx.coroutines.flow.Flow

interface canvasInterface {
    suspend fun getRoutes() :Flow<List<Route>>
    suspend fun insert(drawLines: Route)
    suspend fun insertDrawLine(drawLine:DrawLines)
    suspend fun delete(drawLines: Route)

}