package com.example.englishtutor.data.repository.libraryRepository

import android.util.Log
import com.example.englishtutor.data.api.AppointmentRequest
import com.example.englishtutor.data.api.libraries.LibraryInterface
import com.example.englishtutor.data.exception.ResourceNotFoundException
import com.example.englishtutor.data.model.DoctorListModel
import com.example.englishtutor.data.model.DoctorProfileModel
import retrofit2.HttpException
import javax.inject.Inject

class LibraryRepository @Inject constructor(
    private val libraryInterface: LibraryInterface
) {
    suspend fun getWeekInfoRepo(): Result<List<DoctorListModel>> {
        return try {
            val response = libraryInterface.getWeekInfo()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(Exception("Response body is null"))
                }
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Log.e("LibraryRepository", "Error fetching week info", e)
            Result.failure(e)
        }
    }

    suspend fun getDoctorProfile(id: Int): Result<DoctorProfileModel?> = try {
        val response = libraryInterface.getDoctorProfileByIdInterface(id)

        when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    // 204 No Content or empty successful response
                    Result.success(null)           // or Result.Empty / custom state
                    // or Result.failure(NotFoundException(...))
                }
            }
            response.code() == 404 -> Result.failure(ResourceNotFoundException())
            else -> Result.failure(HttpException(response))
        }
    } catch (e: Exception) {
        Log.e("LibraryRepository", "Error fetching doctor profile", e)
        Result.failure(e)   // network error, serialization error, etc.
    }

    suspend fun submitAppointment(request: AppointmentRequest): Result<Unit> {
        return try {
            val response = libraryInterface.submitAppointment(request)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}