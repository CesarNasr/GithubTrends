package com.example.githubtrends.presentation.utils

import com.example.githubtrends.data.model.response.ApiEntry


sealed class UiState {
    object Empty : UiState()
    object Loading : UiState()
    class Loaded(val itemData : ApiEntry ?= null, val data: List<ApiEntry>? = null, val message: String? = null) : UiState()
    class Error(val error: ErrorType? = null) : UiState()
}

enum class ErrorType{
    InternetError,
    Others
}