package com.example.noteify.Repository

import com.example.noteify.RoomDB.DrawLines
import com.example.noteify.RoomDB.PathDAO
import com.example.noteify.RoomDB.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class canvasRepository @Inject constructor (val DAO : PathDAO):canvasInterface {
    override suspend fun getRoutes(): Flow<List<Route>> {
      return withContext(Dispatchers.IO){DAO.getAllPaths()}
    }

    override suspend fun insert(route: Route) {
       withContext(Dispatchers.IO){DAO.insert(route)}
    }

    override suspend fun insertDrawLine(drawLine: DrawLines) {
         withContext(Dispatchers.IO){DAO.insertDrawLine(drawLine)}
    }



    override suspend fun delete(route: Route){
        withContext(Dispatchers.IO){DAO.delete(route)}
    }
}