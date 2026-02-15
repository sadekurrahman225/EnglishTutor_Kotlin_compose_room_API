package com.example.englishtutor.ui.screens.ProfileScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.englishtutor.util.NetworkResult

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = viewModel()
) {

    LaunchedEffect(Unit) {
        profileViewModel.loadDoctors()
    }

    val state by profileViewModel.state.collectAsState()
    val isRefreshing = state is NetworkResult.Loading

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            profileViewModel.loadDoctors(forceRefresh = true)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {

        when (state) {

            is NetworkResult.Loading -> {
                Text(
                    text = "Loading...",
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is NetworkResult.Success -> {
                val doctors = (state as NetworkResult.Success).data

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(doctors) {
                        Text(
                            text = it.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }
            }

            is NetworkResult.Error -> {
                Text(
                    text = "Error loading data",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}
