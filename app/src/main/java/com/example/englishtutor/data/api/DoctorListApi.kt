package com.example.englishtutor.data.api

import com.example.englishtutor.data.model.DoctorListModel
import retrofit2.http.GET
interface DoctorListApi {

    @GET("planning/doctor_profiles_list")
    suspend fun getDoctorProfiles(): List<DoctorListModel>

}