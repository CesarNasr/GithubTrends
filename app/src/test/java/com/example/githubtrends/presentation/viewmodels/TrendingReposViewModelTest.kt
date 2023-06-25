package com.example.githubtrends.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.MainCoroutineRule
import com.example.githubtrends.data.repositoryimpl.MockRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule


@ExperimentalCoroutinesApi
class TrendingReposViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule  = MainCoroutineRule()  // Might be used when calling Dispatchers.Main in viewmodel

    private lateinit var viewModel: TrendingReposViewModel

    @Before
    fun setup() {
        viewModel = TrendingReposViewModel(MockRepositoryImpl())
    }





}