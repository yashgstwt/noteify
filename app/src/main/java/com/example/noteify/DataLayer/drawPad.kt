package com.example.noteify.DataLayer

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import io.ak1.drawbox.createPath
import androidx.compose.foundation.layout.Box as Box

@Composable
fun DrawingCanvas(drawManager: DrawManager){

    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }

var ShowValues = "LOG"

    Box (
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale = (scale * zoom).coerceIn(0.5f, 7f) // Limit zoom between 0.5x and 5x
                    offsetX += pan.x
                    offsetY += pan.y

                    Log.d(
                        ShowValues,
                        "scale : $scale , offsetX : $offsetX  + pan.x ${pan} : , offsetY : $offsetY  + pan.y ${pan.y} :"
                    )
                }
            }
    ){
        Canvas(modifier = Modifier
            .fillMaxSize()
            .background(drawManager.bgColor)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val transformedOffset = Offset(
                            // (OffsetChange - original offset) / zoom value = current position
                           // x = (50 - 100) / 2 = 25
                            (offset.x - offsetX) / scale,
                            (offset.y - offsetY) / scale
                        )
//                        Log.d(
//                            ShowValues,
//                            "onDragStart ==  X:  ${(offset.x - offsetX) / scale} --  Y:  ${(offset.y - offsetY) / scale} "
//                        )
                        drawManager.insertNewPath(transformedOffset)
                    },
                    onDrag = { change, _ ->
                        val newOffset = Offset(
                            (change.position.x - offsetX) / scale,
                            (change.position.y - offsetY) / scale
                        )
                        Log.d(
                            ShowValues,
                            "onDrag ==  X:  ${(change.position.x - offsetX) / scale} --  Y:  ${(change.position.y - offsetY) / scale} "
                        )
                        drawManager.updateLatestPath(newOffset)
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

            drawManager.pathList.forEach {
                    pw ->
                drawPath(
                    path = createPath (pw.path),
                    color = pw.color,
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

    Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.Bottom) {
        Button(onClick = { drawManager.undo() }, Modifier.size(80.dp)) {
            Text(text = "undo")
        }
        Button(onClick = {drawManager.redo() }, Modifier.size(80.dp)) {
            Text(text = "redo")
        }
    }

}

