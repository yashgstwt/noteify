package com.example.noteify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.noteify.UILayer.DrawingCanvas
import com.example.noteify.notesViewModal.CanvasViewModal
import com.example.noteify.ui.theme.NoteifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteifyTheme {
                val canvasViewModal : CanvasViewModal by viewModels()

                DrawingCanvas(canvasViewModal)
            }
        }
    }
}

