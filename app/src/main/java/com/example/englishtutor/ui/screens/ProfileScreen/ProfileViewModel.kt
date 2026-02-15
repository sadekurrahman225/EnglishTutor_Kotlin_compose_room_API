package com.example.englishtutor.ui.screens.ProfileScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.englishtutor.data.model.DoctorListModel
import com.example.englishtutor.data.repository.libraryRepository.LibraryRepository
import com.example.englishtutor.util.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val libraryRepository = LibraryRepository()
    private val _state =
        MutableStateFlow<NetworkResult<List<DoctorListModel>>>(NetworkResult.Loading)
    val state: StateFlow<NetworkResult<List<DoctorListModel>>> = _state

    init {
        loadDoctors()
    }

    private fun loadDoctors() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading

            libraryRepository.getWeekInfoRepo()
                .onSuccess {
                    _state.value = NetworkResult.Success(it)
                }
                .onFailure {
                    _state.value = NetworkResult.Error(it.message ?: "Unknown error")
                }
        }

    }
}