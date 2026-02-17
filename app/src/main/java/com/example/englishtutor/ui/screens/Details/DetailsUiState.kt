package com.example.englishtutor.ui.screens.details

import com.example.englishtutor.data.model.DoctorProfileModel

sealed class DetailsUiState {
    object Loading : DetailsUiState()
    data class Success(val doctor: DoctorProfileModel?) : DetailsUiState()
    data class Error(val message: String) : DetailsUiState()
    object Submitting : DetailsUiState()
    object SubmissionSuccess : DetailsUiState()
}
