package com.example.englishtutor.ui.screens.Details

import com.example.englishtutor.data.model.DoctorProfileModel

sealed interface DetailsUiState {
    data object Loading : DetailsUiState
    data class Success(val doctor: DoctorProfileModel?) : DetailsUiState
    data class Error(val message: String) : DetailsUiState
}