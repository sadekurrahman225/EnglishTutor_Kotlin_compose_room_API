package com.example.englishtutor.data.api

import retrofit2.http.GET

interface LessonApi {

    @GET("google.com/lessons/api")
    suspend fun getLessons(): List<LessonDto>
}
