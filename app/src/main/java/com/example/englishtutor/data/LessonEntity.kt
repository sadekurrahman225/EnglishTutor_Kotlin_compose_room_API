package com.example.englishtutor.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey
    val id: String = "",
    val title: String = "",
    val description: String = ""
)