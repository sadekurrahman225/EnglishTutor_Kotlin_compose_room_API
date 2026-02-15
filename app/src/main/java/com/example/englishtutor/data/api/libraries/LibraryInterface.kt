package com.example.englishtutor.data.api.libraries

import com.example.englishtutor.data.model.DoctorListModel
import retrofit2.http.GET

interface LibraryInterface {

    @GET("planning/doctor_profiles_list")
    suspend fun getWeekInfo() : List<DoctorListModel>
}