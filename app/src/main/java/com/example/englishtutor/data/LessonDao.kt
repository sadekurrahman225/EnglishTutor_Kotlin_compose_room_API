package com.example.englishtutor.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Insert
    suspend fun insertLesson(lesson: LessonEntity)

    @Query("SELECT * FROM lessons ORDER BY id DESC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)
}
