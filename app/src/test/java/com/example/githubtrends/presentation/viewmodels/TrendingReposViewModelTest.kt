package com.example.githubtrends.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.githubtrends.presentation.utils.UiState
import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.repositoryimpl.MockErrorType
import com.example.githubtrends.data.repositoryimpl.MockRepositoryImpl
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.utils.ErrorType
import com.example.githubtrends.presentation.utils.ResourcesProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class TrendingReposViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule =
        MainCoroutineRule()  // Might be used when calling Dispatchers.Main in viewmodel

    private lateinit var viewModel: TrendingReposViewModel
    private lateinit var mockRepository: MockRepositoryImpl

    @Before
    fun setup() {
        mockRepository = MockRepositoryImpl(ResponseConverter())
        viewModel = TrendingReposViewModel(mockRepository)
    }


    @Test
    fun `fetch trending repos returns success`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.None)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Loaded())
    }


    @Test
    fun `fetch trending repos returns internet error`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.InternetError)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Error(error = ErrorType.InternetError))
    }


    @Test
    fun `fetch trending repos returns other error`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.Others)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Error(error = ErrorType.Others))
    }


}