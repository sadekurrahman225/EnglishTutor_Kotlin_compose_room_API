package com.example.englishtutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishtutor.data.repository.LessonRepository
import com.example.englishtutor.data.LessonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(private val lessonRepository: LessonRepository) : ViewModel() {

    val lessons: StateFlow<List<LessonEntity>> = lessonRepository.getAllLessons()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: ${throwable.message}")
    }

    init {
        syncLessons()
    }

    private fun syncLessons() {
        viewModelScope.launch(exceptionHandler) {
            try {
                println("API sync started")
                lessonRepository.syncLessonsFromApi()
            } catch (e: Exception) {
                // This will now catch exceptions from the suspend function
                println("API sync failed: ${e.message}")
            }
        }
    }

    fun addLesson() {
        viewModelScope.launch {
            lessonRepository.insertLesson(
                LessonEntity(
                    title = "Lesson ${lessons.value.size + 1}",
                    description = "Basic English sentence"
                )
            )
        }
    }

    fun updateLesson(lesson: LessonEntity) {
        viewModelScope.launch {
            lessonRepository.updateLesson(lesson)
        }
    }

    fun deleteLesson(lesson: LessonEntity) {
        viewModelScope.launch {
            lessonRepository.deleteLesson(lesson)
        }
    }
}
