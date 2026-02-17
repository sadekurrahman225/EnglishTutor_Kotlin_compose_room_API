package com.example.englishtutor.di

import com.example.englishtutor.data.api.LessonApi
import com.example.englishtutor.data.api.LibraryApi
import com.example.englishtutor.data.api.libraries.LibraryInterface
import com.example.englishtutor.data.repository.libraryRepository.LibraryRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://202.4.102.250:7964/lr_api/index.php/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLessonApi(retrofit: Retrofit): LessonApi {
        return retrofit.create(LessonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLibraryApi(retrofit: Retrofit): LibraryApi {
        return retrofit.create(LibraryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideLibraryInterface(retrofit: Retrofit): LibraryInterface {
        return retrofit.create(LibraryInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideLibraryRepository(libraryInterface: LibraryInterface): LibraryRepository {
        return LibraryRepository(libraryInterface)
    }
}