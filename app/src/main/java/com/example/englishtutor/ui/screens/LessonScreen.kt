package com.example.englishtutor.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.englishtutor.data.*
import kotlinx.coroutines.launch

@Composable
fun LessonScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val dao = db.lessonDao()

    val lessons by dao.getAllLessons().collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {

        Button(onClick = {
            scope.launch {
                dao.insertLesson(
                    LessonEntity(
                        title = "Lesson ${lessons.size + 1}",
                        description = "Basic English sentence"
                    )
                )
            }
        }) {
            Text("Add Lesson")
        }

        Spacer(modifier = Modifier.height(16.dp))

        lessons.forEach { lesson ->
            Text(text = lesson.title)
        }
    }
}
