package com.example.englishtutor.data.mapper

import com.example.englishtutor.data.LessonEntity
import com.example.englishtutor.data.api.LessonDto

fun LessonDto.toEntity() = LessonEntity(
    id = id,
    title = title,
    description = description
)
