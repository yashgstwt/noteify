package com.example.noteify.notesViewModal

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteify.Repository.canvasRepository
import com.example.noteify.RoomDB.DataConverters
import com.example.noteify.RoomDB.DrawLines
import com.example.noteify.RoomDB.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

@HiltViewModel
class CanvasViewModal @Inject constructor (repository: canvasRepository) : ViewModel() {
    var loge = "CanvasViewModal"
    val Repository :canvasRepository = repository

    var insertList : MutableList<DrawLines?> = mutableListOf()

    var canvasList  : MutableList<Route> = mutableListOf()


    private val latch = CountDownLatch(1) // Initialize latch with count 1

    init {
        Log.d(loge, "init called of viewModal")
            viewModelScope.launch {
            repository.getRoutes().collectLatest {
                it.forEach { route ->
                    canvasList.add(route)
                }
                latch.countDown() // Decrement latch count when collection is done
            }
        }
    }


    var selectedIndex by mutableIntStateOf(0)
    var selectedCanvas =  Route(id = 1, path = null )
    init {
        viewModelScope.launch(Dispatchers.IO) {
            latch.await() // Wait for latch to reach zero

             selectedCanvas  = if(!canvasList.isEmpty()){ canvasList[selectedIndex] } else {
                Route(id = 1, path = null )
            }
            for (i in canvasList) {
                Log.d(loge, "CANVAS LIST IS :   ${i.id} :  ${i.path} \n  ")
            }
        }
    }



    private val _undoList = mutableStateListOf<DrawLines?>()
    private val _redoList = mutableStateListOf<DrawLines?>()
            val _pathList : MutableList<DrawLines?> = _undoList

    var bgColor by mutableStateOf(Color.Black)
        private set

    var penColor by  mutableLongStateOf(0xFFFFFFFF)
        private set

    var strokeWidth by  mutableFloatStateOf(5f)
        private set

    var alpha by mutableFloatStateOf(1f)
        private set


    fun insertNewPath (offset : Offset){

        val lines = DrawLines(
            id = 0,
            path = mutableStateListOf(Pair(offset.x , offset.y)),
            color = penColor,
            strokeWidth = strokeWidth,
            alpha = alpha
        )
        _undoList.add(lines)
        _redoList.clear()
    }

    fun updateLatestPath(newPoint : Offset){
        val index = _undoList.lastIndex
        _undoList[index]?.path?.add(Pair(newPoint.x , newPoint.y))
    }

    fun undo (){
        if (_undoList.isNotEmpty()){
            _redoList.add(_undoList.removeLast())
        }
    }

    fun redo (){
        if (_redoList.isNotEmpty()){
            val redoLast = _redoList[_redoList.lastIndex]
            _undoList.add(redoLast)
            _redoList.removeAt(_redoList.lastIndex)

        }
    }


    fun updateRoute(){
        viewModelScope.launch {

            _pathList.forEach { value ->
                insertList.add(value)
            }

           val StringConverter = DataConverters().fromDrawLinesList(insertList)

            Repository.DAO.insert(Route(id = 1, path =StringConverter ))

        }


    }


//    override fun onCleared() {
//        //try changing pathList with undoPathList
//
//        viewModelScope.launch {
//            Repository.DAO.insert(Route(id = selectedIndex , path = _pathList))
//        }
//
//        Log.d(loge , "onCleared is called from viewModal : ${pathList}")
//    }


    //    fun addNewCanvas( repository: canvasRepository ) = viewModelScope.launch {
//        repository.insert(Route(id = 0, path = mutableListOf()))
//    }



}