package com.example.englishtutor.ui.screens.ProfileScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.englishtutor.data.api.ListApiOjbects
import com.example.englishtutor.data.model.DoctorListModel

@Composable
fun ProfileScreen() {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(text = "Profile Screen")
//    }

    var doctors by remember { mutableStateOf<List<DoctorListModel>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        try {
            doctors = ListApiOjbects.api.getDoctorProfiles()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    LazyColumn {
        items(doctors) { doctor ->
            Text(text = doctor.name)
        }
    }
}
