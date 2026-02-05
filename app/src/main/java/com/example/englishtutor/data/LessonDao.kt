package com.example.englishtutor.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LessonDao {

    @Query("SELECT * FROM lessons")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Insert
    suspend fun insertLesson(lesson: LessonEntity)

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)
}