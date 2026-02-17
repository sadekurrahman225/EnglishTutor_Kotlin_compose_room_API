package com.example.englishtutor.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishtutor.data.api.AppointmentRequest
import com.example.englishtutor.data.repository.libraryRepository.LibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsPageViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun fetchLessonDetails(id: Int) {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading
            libraryRepository.getDoctorProfile(id)
                .onSuccess {
                    _uiState.value = DetailsUiState.Success(it)
                }
                .onFailure {
                    _uiState.value = DetailsUiState.Error(it.message ?: "Unknown error")
                }
        }
    }

    fun submitAppointment(userId: Int, phoneNumber: String, doctorId: Int, note: String) {
        viewModelScope.launch {
            _uiState.value = DetailsUiState.Submitting
            val request = AppointmentRequest(
                userId = userId,
                phoneNumber = phoneNumber,
                doctorId = doctorId,
                note = note
            )
            libraryRepository.submitAppointment(request)
                .onSuccess {
                    _uiState.value = DetailsUiState.SubmissionSuccess
                }
                .onFailure {
                    _uiState.value = DetailsUiState.Error(it.message ?: "Failed to submit appointment")
                }
        }
    }
}