package com.example.githubtrends.presentation.utils

import com.example.githubtrends.presentation.model.UiApiEntry


sealed class UiState {
    object Empty : UiState()
    object Loading : UiState()
    class Loaded(val itemData : UiApiEntry?= null, val message: String? = null) : UiState()
    class Error(val error: ErrorType? = null) : UiState()
}

enum class ErrorType{
    InternetError,
    Others
}