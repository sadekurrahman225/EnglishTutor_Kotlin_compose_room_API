package com.example.englishtutor.data.repository

import com.example.englishtutor.data.LessonDao
import com.example.englishtutor.data.LessonEntity
import com.example.englishtutor.data.api.LessonApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor(
    private val lessonDao: LessonDao,
    private val lessonApi: LessonApi
) {

    fun getAllLessons(): Flow<List<LessonEntity>> = lessonDao.getAllLessons()

    suspend fun insertLesson(lesson: LessonEntity) {
        lessonDao.insertLesson(lesson)
    }

    suspend fun updateLesson(lesson: LessonEntity) {
        lessonDao.updateLesson(lesson)
    }

    suspend fun deleteLesson(lesson: LessonEntity) {
        lessonDao.deleteLesson(lesson)
    }

    suspend fun syncLessonsFromApi() {
        val lessons = lessonApi.getLessons()
        lessons.forEach { lessonDto ->
            lessonDao.insertLesson(
                LessonEntity(
                    id = lessonDto.id,
                    title = lessonDto.title,
                    description = lessonDto.description
                )
            )
        }
    }
}