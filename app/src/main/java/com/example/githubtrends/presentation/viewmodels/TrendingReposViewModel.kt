package com.example.githubtrends.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.utils.ErrorType
import com.example.githubtrends.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject


@HiltViewModel
class TrendingReposViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _trendingReposUiState = MutableStateFlow<UiState>(UiState.Loading)
    val trendingReposUiState: StateFlow<UiState> = _trendingReposUiState

    init {
        fetchTrendingRepos()
    }

    fun fetchTrendingRepos() {
        viewModelScope.launch {
            try {
                when (val response = repository.fetchTrendingRepos()) {
                    is Resource.Success -> {
                        response.data?.let {
                            _trendingReposUiState.value = UiState.Loaded(itemData = response.data)
                        }
                    }

                    is Resource.Error -> {
                        onErrorOccurred(false)
                    }

                    is Resource.Loading -> {
                        _trendingReposUiState.value = UiState.Loading
                    }
                }
            } catch (e: Exception) {
                onErrorOccurred(e is HttpException)
            }

        }
    }


    private fun onErrorOccurred(isInternetError: Boolean) {
        _trendingReposUiState.value =
            if (isInternetError) UiState.Error(ErrorType.InternetError) else UiState.Error(ErrorType.Others)
    }

}