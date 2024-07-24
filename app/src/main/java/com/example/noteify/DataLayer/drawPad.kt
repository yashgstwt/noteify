package com.example.noteify.DataLayer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import io.ak1.drawbox.createPath

@Composable
fun DrawingCanvas(drawManager: DrawManager){

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(9f)
        .background(drawManager.bgColor)
        .pointerInput(Unit) {
            detectDragGestures(onDragStart = { offset ->
                drawManager.insertNewPath(offset)
            }) { change, _ ->
                val newOffset = change.position
                drawManager.updateLatestPath(newOffset)
            }

        } ) {
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



    Row(modifier= Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly , verticalAlignment = Alignment.Bottom) {
        Button(onClick = { drawManager.undo() }, Modifier.size(80.dp)) {
            Text(text = "undo")
        }
        Button(onClick = {drawManager.redo() }, Modifier.size(80.dp)) {
            Text(text = "redo")
        }
    }

}

