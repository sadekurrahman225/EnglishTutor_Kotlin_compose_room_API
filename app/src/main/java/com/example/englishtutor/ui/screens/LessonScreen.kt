package com.example.englishtutor.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.englishtutor.ui.screens.LessonItem
import com.example.englishtutor.viewmodel.LessonViewModel

@Composable
fun LessonScreen(
    modifier: Modifier = Modifier,
    viewModel: LessonViewModel = hiltViewModel()
) {
    val lessons by viewModel.lessons.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = { viewModel.addLesson() }) {
            Text("Add Lesson")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(lessons, key = { it.id }) { lesson ->
                LessonItem(
                    lesson = lesson,
                    onUpdate = { viewModel.updateLesson(it) },
                    onDelete = { viewModel.deleteLesson(it) }
                )
            }
        }
    }
}