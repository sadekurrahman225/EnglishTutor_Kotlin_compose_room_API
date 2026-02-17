package com.example.englishtutor.data.repository

import com.example.englishtutor.data.api.AppointmentRequest

interface LibraryInterface {
    suspend fun submitAppointment(appointmentRequest: AppointmentRequest): Any
}
