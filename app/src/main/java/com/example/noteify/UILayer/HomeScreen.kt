package com.example.noteify.UILayer

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.noteify.Repository.canvasRepository
import com.example.noteify.notesViewModal.CanvasViewModal

@Composable
fun HomeScreen(viewModal: CanvasViewModal ) {

    Surface(modifier = Modifier.fillMaxSize()) {
Log.d("test from main " , "home screen is opened ")
        Scaffold( floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

//            if ( viewModal.isListIsEmpty ){
//                Text(text = "add canvas to start", modifier = Modifier.fillMaxSize())
//            }else
            DrawingCanvas(viewModal,innerPadding)
        }
    }
}