package com.example.englishtutor.data.api

import com.google.gson.annotations.SerializedName

data class AppointmentRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("doctor_id")
    val doctorId: Int,
    val note: String
)