package com.example.githubtrends.presentation.utils

import com.example.githubtrends.presentation.model.UiApiEntry

/**
 * UiState is onl responsible for managing the UI state, should not be used in other layers
 * */
sealed class UiState {
    object Loading : UiState()
    class Loaded(val itemData : UiApiEntry?= null, val message: String? = null) : UiState()
    class Error(val error: ErrorType? = null) : UiState()
}

enum class ErrorType{
    InternetError,
    Others
}