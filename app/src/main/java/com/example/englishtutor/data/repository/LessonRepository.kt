package com.example.englishtutor.data.repository

import com.example.englishtutor.data.LessonDao
import com.example.englishtutor.data.LessonEntity
import com.example.englishtutor.data.api.LessonApi
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LessonRepository @Inject constructor(
    private val lessonDao: LessonDao,
    private val lessonApi: LessonApi,
    private val firebaseDatabase: FirebaseDatabase
) {

    private val databaseReference = firebaseDatabase.getReference("lessons")

    init {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                CoroutineScope(Dispatchers.IO).launch {
                    val lessons = mutableListOf<LessonEntity>()
                    snapshot.children.forEach { childSnapshot ->
                        val lessonMap = childSnapshot.value as? Map<String, @JvmSuppressWildcards Any>
                        lessonMap?.let {
                            val id = childSnapshot.key
                            val title = it["title"] as? String
                            val description = it["description"] as? String

                            if (id != null && title != null && description != null) {
                                lessons.add(LessonEntity(id, title, description))
                            }
                        }
                    }
                    lessonDao.deleteAll()
                    lessonDao.insertAll(lessons)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Firebase sync failed: ${error.message}")
            }
        })
    }

    fun getAllLessons(): Flow<List<LessonEntity>> = lessonDao.getAllLessons()

    suspend fun insertLesson(lesson: LessonEntity) {
        databaseReference.child(lesson.id).setValue(lesson)
    }

    suspend fun updateLesson(lesson: LessonEntity) {
        databaseReference.child(lesson.id).setValue(lesson)
    }

    suspend fun deleteLesson(lesson: LessonEntity) {
        databaseReference.child(lesson.id).removeValue()
    }

    suspend fun syncLessonsFromApi() {
        try {
            val lessons = lessonApi.getLessons()
            lessons.forEach { lessonDto ->
                val newLesson = LessonEntity(
                    id = UUID.randomUUID().toString(),
                    title = lessonDto.title,
                    description = lessonDto.description
                )
                insertLesson(newLesson)
            }
        } catch (e: Exception) {
            println("API sync failed: ${e.message}")
        }
    }
}