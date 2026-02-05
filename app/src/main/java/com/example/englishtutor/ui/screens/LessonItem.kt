package com.example.englishtutor.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.englishtutor.data.LessonEntity

@Composable
fun LessonItem(
    lesson: LessonEntity,
    onUpdate: (LessonEntity) -> Unit,
    onDelete: (LessonEntity) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(lesson.title) }
    var description by remember { mutableStateOf(lesson.description) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            if (isEditing) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    Button(onClick = {
                        onUpdate(
                            lesson.copy(
                                title = title,
                                description = description
                            )
                        )
                        isEditing = false
                    }) {
                        Text("Save")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedButton(onClick = { isEditing = false }) {
                        Text("Cancel")
                    }
                }

            } else {
                Text(text = lesson.title, style = MaterialTheme.typography.titleMedium)
                Text(text = lesson.description)

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    TextButton(onClick = { isEditing = true }) {
                        Text("Edit")
                    }

                    TextButton(onClick = { onDelete(lesson) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}
