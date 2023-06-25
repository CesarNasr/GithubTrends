package com.example.githubtrends.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.githubtrends.presentation.utils.UiState
import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.repositoryimpl.MockRepositoryImpl
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
    var mainCoroutineRule  = MainCoroutineRule()  // Might be used when calling Dispatchers.Main in viewmodel

    private lateinit var viewModel: TrendingReposViewModel

    @Before
    fun setup() {
        val mockRepository = MockRepositoryImpl(ResponseConverter())
        viewModel = TrendingReposViewModel(mockRepository)
    }



    @Test
    fun `fetch trending repos returns success`() {
        val results : List<ApiEntry> = viewModel.fetchTrendingRepos()


    }


}