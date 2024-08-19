package com.example.noteify.notesViewModal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteify.Repository.canvasRepository
import com.example.noteify.RoomDB.DrawLines
import com.example.noteify.RoomDB.Route
import com.example.noteify.RoomDB.defaultRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CanvasViewModal @Inject constructor (repository: canvasRepository) : ViewModel() {

    var _canvasList = MutableStateFlow(emptyList<Route>())
    var canvasList = _canvasList.asStateFlow()
    fun addNewCanvas( repository: canvasRepository ) = viewModelScope.launch {
        repository.insert(Route(id = 0, path = mutableListOf()))
    }

    init {
        viewModelScope.launch {
            repository.insert(defaultRoute)
            repository.getRoutes().collectLatest {
            _canvasList.tryEmit(it)

            }
        }
    }

    var isListIsEmpty  =  canvasList.value.isEmpty()
    var selectedIndex by mutableIntStateOf(0)

    var selectedCanvas  = if(!isListIsEmpty){ canvasList.value[selectedIndex] } else{
        Route(id = 0, path = mutableListOf())
    }

    private val _undoList = mutableStateListOf<DrawLines>()
    private val _redoList = mutableStateListOf<DrawLines>()
    private val pathList : SnapshotStateList<DrawLines> = _undoList

    var bgColor by mutableStateOf(Color.Black)
        private set

    var penColor by  mutableLongStateOf( 0xFFFFFFFF)
        private set

    var strokeWidth by  mutableFloatStateOf(5f)
        private set

    var alpha by mutableFloatStateOf(1f)
        private set

    var undoCount by mutableIntStateOf(0)
        private set

    var redoCount by mutableIntStateOf(0)
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
        _undoList[index].path.add(Pair(newPoint.x , newPoint.y))
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

    override fun onCleared() {
        //try changing pathList with undoPathList
        selectedCanvas.path.addAll(pathList)
    }
}