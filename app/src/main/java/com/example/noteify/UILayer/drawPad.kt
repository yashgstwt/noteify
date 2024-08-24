package com.example.noteify.UILayer

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.noteify.RoomDB.DataConverters
import com.example.noteify.notesViewModal.CanvasViewModal
import io.ak1.drawbox.createPath

@Composable
fun DrawingCanvas(viewModal : CanvasViewModal , paddingValues: PaddingValues ){

    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }


val ShowValues = "LOG"

    Box (
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale = 1f // Limit zoom between 1x and 2x
                    offsetX += pan.x
                    offsetY += pan.y

                    Log.d(
                        ShowValues,
                        "scale : $scale , offsetX : $offsetX  + pan.x ${pan.x} : , offsetY : $offsetY  + pan.y ${pan.y} :"
                    )
                }
            }
    ){
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(viewModal.bgColor)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val transformedOffset = Offset(
                            // (OffsetCurrent - original offset) / zoom value = current position
                            // x = (50 - 100) / 2 = 25
                            (offset.x - offsetX),
                            (offset.y - offsetY)
                        )
                        // currentPointer = transformedOffset
//                        Log.d(
//                            ShowValues,
//                            "onDragStart == offset.x : ${offset.x} - offsetX : ${offsetX} / scale :${scale} = ${(offset.x - offsetX) } --  Y:  ${(offset.y - offsetY) / scale} "
//                        )
                        viewModal.insertNewPath(transformedOffset)
                    },
                    onDrag = { change, _ ->
                        //change.position.x : it shows the current position of the pointer on the input screen , if we touch on 200 without zoom or pan it is 200 , and even if we zoom or pan it will show the current position i.e 200f
                        val newOffset = Offset(
                            (change.position.x - offsetX),
                            (change.position.y - offsetY)
                        )
//                        Log.d(
//                            ShowValues,
//                            "onDrag == change.position.x : ${change.position.x} - offsetX : ${offsetX} / scale ${scale} = ${(change.position.x - offsetX) } --  Y:  ${(change.position.y - offsetY) / scale} "
//                        )
                        viewModal.updateLatestPath(newOffset)
                    }
                )
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                translationX = offsetX
                translationY = offsetY
            }
            )
        {
            //remove it after
            drawLine(Color.Red , start = Offset.Zero , end = Offset(0f, size.height), strokeWidth = 5f)
            //draws line on canvas
            drawLine(Color.Blue , start =  Offset.Zero , end = Offset(size.width, 0f), strokeWidth = 5f)

            viewModal.pathList.forEach {
                    pw ->
                var drawingOffsetList : MutableList<Offset> = emptyList<Offset>().toMutableList()
                //.onEach { Log.d(ShowValues, " in drawing phase :: X: ${it.x} + Y: ${it.y}") }
                pw?.path?.onEach {
                    val tempOffset = Offset(it.first,it.second)
                    drawingOffsetList.add(tempOffset)
                }
                val color = if (pw?.color == null) 0xFFFFFFFF else pw.color
                val windth = if (pw?.strokeWidth == null) 5f else pw.strokeWidth
                drawPath(
                    path = createPath(drawingOffsetList) ,
                    color = Color(color) ,
                    style = Stroke(
                        width = windth,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )

            }
            val DrawLinesList = viewModal.selectedCanvas.path?.let {
                DataConverters().toDrawLinesList(it)
            }

            DrawLinesList?.forEach {
                    pw ->
                var offsetList : MutableList<Offset> = emptyList<Offset>().toMutableList()
                //.onEach { Log.d(ShowValues, " in drawing phase :: X: ${it.x} + Y: ${it.y}") }
                pw?.path?.onEach {
                val tempOffset :Offset = Offset(it.first,it.second)
                 offsetList.add(tempOffset)
                }
                if (pw != null) {
                    drawPath(
                        path = createPath(offsetList) ,
                        color = Color(pw.color) ,
                        alpha = pw.alpha,
                        style = Stroke(
                            width = pw.strokeWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }
        }
    }

    Row(modifier= Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.Bottom) {
        Button(onClick = { viewModal.undo() }, Modifier.size(80.dp,40.dp)) {
            Text(text = "undo")
        }
        Button(onClick = {viewModal.redo() }, Modifier.size(80.dp, 40.dp)) {
            Text(text = "redo")
        }
        Button(onClick = { viewModal.updateRoute() },Modifier.size(80.dp, 40.dp)) {
            Text(text = "Update")
        }
    }
}

