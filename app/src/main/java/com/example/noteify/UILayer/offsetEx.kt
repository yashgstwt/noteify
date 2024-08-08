package com.example.noteify.UILayer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun Ex (){
    val path = mutableListOf(Offset(12f,15f) , Offset(15f,50f),Offset(20f,100f), Offset(25f, 90f))
    Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(Color.Red , start = Offset.Zero , end = Offset(0f, size.height), strokeWidth = 15f)

            drawLine(Color.Blue , start =  Offset.Zero , end = Offset(size.width- 200, 0f), strokeWidth = 15f)
            drawArc(startAngle = 0f , sweepAngle = 250f , color = Color.Red , useCenter = true , topLeft = Offset(200f,200f))
    }
}