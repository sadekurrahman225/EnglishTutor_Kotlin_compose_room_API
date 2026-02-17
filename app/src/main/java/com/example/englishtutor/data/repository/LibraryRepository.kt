package com.example.englishtutor.data.repository

import com.example.englishtutor.data.api.AppointmentRequest
import com.example.englishtutor.data.api.LibraryApi
import javax.inject.Inject

class LibraryRepository @Inject constructor(
    private val libraryApi: LibraryApi
) : LibraryInterface {
    override suspend fun submitAppointment(appointmentRequest: AppointmentRequest): Any {
        return libraryApi.submitAppointment(appointmentRequest)
    }
}