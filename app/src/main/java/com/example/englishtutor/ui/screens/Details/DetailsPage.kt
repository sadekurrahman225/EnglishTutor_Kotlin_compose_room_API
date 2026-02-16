package com.example.englishtutor.ui.screens.Details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsPage(id: Int, viewModel: DetailsPageViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = id) {
        viewModel.fetchDoctorDetails(id)
    }
    // Observe the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Render UI based on the current state
        when (val state = uiState) {
            is DetailsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is DetailsUiState.Success -> {
                val doctor = state.doctor
                if (doctor != null) {
                    Column {
                        Text(text = "Doctor Name: ${doctor.name}")
                        Text(text = "id: ${doctor.id}")
                        // Add other doctor details here
                    }
                } else {
                    Text(text = "Doctor not found")
                }
            }
            is DetailsUiState.Error -> {
                Text(text = state.message)
            }
        }
    }
}