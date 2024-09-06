package com.example.noteify.UILayer

import android.Manifest
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.noteify.notesViewModal.CanvasViewModal
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(viewModal: CanvasViewModal ) {

    Box(modifier = Modifier.fillMaxSize()) {
Log.d("test from main " , "home screen is opened ")
        Scaffold( floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

            val permissionsState = rememberPermissionState(
                permission = Manifest.permission.READ_EXTERNAL_STORAGE
            )

            if (permissionsState.status.isGranted) {
                // Permission granted, access the file system
                DrawingCanvas(viewModal,innerPadding)
            } else {
                Column {
                    val textToShow = if (permissionsState.status.shouldShowRationale) {
                        // If the user has denied the permission but not permanently
                        "The app needs access to the file system to read files."
                    } else {
                        // If the user has permanently denied the permission
                        "Permission denied. Go to settings to enable."
                    }

                    Text(text = textToShow)
                    Button(onClick = { permissionsState.launchPermissionRequest() }) {
                        Text("Request permission")
                    }
                }
            }
        }
    }
}