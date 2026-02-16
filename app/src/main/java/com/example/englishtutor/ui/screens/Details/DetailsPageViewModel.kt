package com.example.englishtutor.ui.screens.Details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishtutor.data.repository.libraryRepository.LibraryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsPageViewModel @Inject constructor(
    private val libraryRepository: LibraryRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun fetchDoctorDetails(id: Int) {
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
}