package com.example.githubtrends.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.githubtrends.presentation.utils.UiState
import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.repositoryimpl.MockErrorType
import com.example.githubtrends.data.repositoryimpl.MockRepositoryImpl
import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.utils.ErrorType
import com.example.githubtrends.presentation.utils.ResourcesProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Manually mocked the repository implementation in order to test the repo response, data mapping, and viewmodel states
 * Could have also used Mockito for more sophisticated classes to mock (ex: mocking retrofit instances, etc..)
 * */

@ExperimentalCoroutinesApi
class TrendingReposViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()  // Might be used when calling Dispatchers.Main in viewmodel

    private lateinit var viewModel: TrendingReposViewModel
    private lateinit var mockRepository: MockRepositoryImpl

    @Before
    fun setup() {
        mockRepository = MockRepositoryImpl(ResponseConverter(), ItemMapper())
        viewModel = TrendingReposViewModel(mockRepository)
    }


    @SuppressLint("CheckResult")
    @Test
    fun `fetch trending repos returns success`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.None)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Loaded())
    }


    @SuppressLint("CheckResult")
    @Test
    fun `fetch trending repos returns internet error`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.InternetError)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Error(error = ErrorType.InternetError))
    }


    @SuppressLint("CheckResult")
    @Test
    fun `fetch trending repos returns other error`() {
        mockRepository.setShouldReturnNetworkError(MockErrorType.Others)

        viewModel.fetchTrendingRepos()
        val results = viewModel.trendingReposUiState.value

        assertThat(results == UiState.Error(error = ErrorType.Others))
    }


}