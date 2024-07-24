package com.example.noteify.DataLayer


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

class DrawManager {

    private val _undoList = mutableStateListOf<Lines>()
    private val _redoList = mutableStateListOf<Lines>()
    internal val pathList : SnapshotStateList<Lines> = _undoList

    var bgColor by mutableStateOf(Color.White)
    private set

    var penColor by  mutableStateOf(Color.Black)
    private set

    var strokeWidth by  mutableFloatStateOf(5f)
    private set

    var alpha by mutableFloatStateOf(1f)
    private set

    var undoCount by mutableIntStateOf(0)
    private set

    var redoCount by mutableIntStateOf(0)
    private set

    //for single draw
    data class Lines(
        var path: SnapshotStateList<Offset>,
        val color:Color,
        val strokeWidth: Float ,
        val alpha: Float = 0f,
    )

    //for collection of draws used for storing the each draw activity
    data class route(
        val bgColor: Color,
        val path: List<Lines>,
    )

    fun insertNewPath (offset :Offset){
        val lines = Lines(
            path = mutableStateListOf(offset),
            color = penColor,
            strokeWidth = strokeWidth,
            alpha = alpha
        )
        _undoList.add(lines)
        _redoList.clear()
    }

     fun updateLatestPath(newPoint :Offset){
         val index = _undoList.lastIndex
         _undoList[index].path.add(newPoint)

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

}