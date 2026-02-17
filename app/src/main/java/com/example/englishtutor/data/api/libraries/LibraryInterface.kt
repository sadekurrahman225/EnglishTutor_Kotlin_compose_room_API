package com.example.englishtutor.data.api.libraries

import com.example.englishtutor.data.api.AppointmentRequest
import com.example.englishtutor.data.model.DoctorListModel
import com.example.englishtutor.data.model.DoctorProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LibraryInterface {

    @GET("planning/doctor_profiles_list")
    suspend fun getWeekInfo() : Response<List<DoctorListModel>>

    @GET("planning/doctor_profile_by_id/id/{doctorId}")
    suspend fun getDoctorProfileByIdInterface(@Path("doctorId") id: Int) : Response<DoctorProfileModel>

    @POST("planning/appointment_submit")
    suspend fun submitAppointment(@Body request: AppointmentRequest): Response<Unit>
}