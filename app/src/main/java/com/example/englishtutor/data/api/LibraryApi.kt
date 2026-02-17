package com.example.englishtutor.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface LibraryApi {
    @POST("lr_api/index.php/api/planning/appointment_submit")
    suspend fun submitAppointment(@Body appointmentRequest: AppointmentRequest): Any
}
