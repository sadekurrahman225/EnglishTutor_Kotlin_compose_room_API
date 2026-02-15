package com.example.englishtutor.data.repository.libraryRepository

import com.example.englishtutor.data.api.libraries.LibraryApiObject
import com.example.englishtutor.data.api.libraries.LibraryInterface
import com.example.englishtutor.data.model.DoctorListModel

class LibraryRepository(
    private val libraryInterface: LibraryInterface = LibraryApiObject.libraryInterface
) {
    suspend fun getWeekInfoRepo(): Result<List<DoctorListModel>> {
        return try {
            val response = libraryInterface.getWeekInfo()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}