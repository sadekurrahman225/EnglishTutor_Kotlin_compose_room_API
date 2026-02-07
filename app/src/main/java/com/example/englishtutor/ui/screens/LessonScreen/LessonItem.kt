package com.example.englishtutor.ui.screens.lessonscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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