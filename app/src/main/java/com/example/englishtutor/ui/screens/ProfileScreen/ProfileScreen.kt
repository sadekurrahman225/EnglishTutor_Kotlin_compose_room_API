package com.example.englishtutor.ui.screens.ProfileScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishtutor.data.model.DoctorListModel
import com.example.englishtutor.util.NetworkResult

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Profile Screen")
//    }

    LaunchedEffect(Unit) {
        profileViewModel.loadDoctors()
    }

    val state by profileViewModel.state.collectAsState()

    when (state) {
        is NetworkResult.Loading -> {
            Text("Loading...")
        }

        is NetworkResult.Success -> {
            val doctors = (state as NetworkResult.Success).data
            LazyColumn {
                items(doctors) {
                    Text(it.name)
                }
            }
        }

        is NetworkResult.Error -> {
            Text("Error loading data")
        }
    }

//    var doctors by remember { mutableStateOf<List<DoctorListModel>>(emptyList()) }
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(Unit) {
//        try {
//            doctors = ListApiOjbects.api.getDoctorProfiles()
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//
//    LazyColumn {
//        items(doctors) { doctor ->
//            Text(text = doctor.name)
//        }
//    }
}
