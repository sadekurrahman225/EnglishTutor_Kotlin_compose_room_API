package com.example.englishtutor.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor(private val lessonDao: LessonDao) {

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
}