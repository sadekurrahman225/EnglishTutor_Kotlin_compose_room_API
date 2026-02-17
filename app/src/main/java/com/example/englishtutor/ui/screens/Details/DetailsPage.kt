package com.example.englishtutor.ui.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.englishtutor.data.model.DoctorProfileModel

@Composable
fun DetailsPage(id: Int, navController: NavController, viewModel: DetailsPageViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = id) {
        viewModel.fetchLessonDetails(id)
    }
    // Observe the UI state from the ViewModel
    val uiState by viewModel.uiState.collectAsState()

    DetailsScreen(uiState = uiState, doctorId = id) { userId, phoneNumber, doctorId, note ->
        viewModel.submitAppointment(userId, phoneNumber, doctorId, note)
    }

    if (uiState is DetailsUiState.SubmissionSuccess) {
        LaunchedEffect(Unit) {
            navController.popBackStack()
        }
    }
}

@Composable
fun DetailsScreen(
    uiState: DetailsUiState,
    doctorId: Int,
    onSubmit: (Int, String, Int, String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Render UI based on the current state
        when (uiState) {
            is DetailsUiState.Loading -> {
                CircularProgressIndicator()
            }
            is DetailsUiState.Success -> {
                val doctor = uiState.doctor
                if (doctor != null) {
                    Column {
                        Text(text = "Doctor Name: ${doctor.name}")
                        Text(text = "id: ${doctor.id}")

                        TextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            label = { Text("Phone Number") }
                        )
                        TextField(
                            value = note,
                            onValueChange = { note = it },
                            label = { Text("Note") }
                        )

                        Button(onClick = { onSubmit(1, phoneNumber, doctorId, note) }) {
                            Text("Submit Appointment")
                        }
                    }
                } else {
                    Text(text = "Doctor not found")
                }
            }
            is DetailsUiState.Error -> {
                Text(text = uiState.message)
            }
            is DetailsUiState.Submitting -> {
                CircularProgressIndicator()
            }
            is DetailsUiState.SubmissionSuccess -> {
                Text("Appointment submitted successfully!")
            }
        }
    }
}

@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DetailsPagePreview() {
    DetailsScreen(
        uiState = DetailsUiState.Success(
            DoctorProfileModel(
                id = 1,
                name = "Dr. John Doe",
                photo = ""
            )
        ),
        doctorId = 1
    ) { _, _, _, _ ->

    }
}