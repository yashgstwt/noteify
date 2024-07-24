package com.example.noteify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.noteify.DataLayer.DrawManager
import com.example.noteify.DataLayer.DrawingCanvas
import com.example.noteify.ui.theme.NoteifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteifyTheme {
                val  drawManager = DrawManager()
                DrawingCanvas(drawManager)
            }
        }
    }
}

