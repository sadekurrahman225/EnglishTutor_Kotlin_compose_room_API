package com.example.englishtutor.di

import com.example.englishtutor.data.api.libraries.LibraryInterface
import com.example.englishtutor.data.repository.libraryRepository.LibraryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLibraryRepository(libraryInterface: LibraryInterface): LibraryRepository {
        return LibraryRepository(libraryInterface)
    }
}
