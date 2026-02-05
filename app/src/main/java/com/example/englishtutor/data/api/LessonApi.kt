package com.example.englishtutor.data.api

import retrofit2.http.GET

interface LessonApi {

    @GET("lessons/api")
    suspend fun getLessons(): List<LessonDto>
}
