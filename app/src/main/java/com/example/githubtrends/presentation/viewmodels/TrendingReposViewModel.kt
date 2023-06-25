package com.example.githubtrends.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.domain.repository.Repository
import javax.inject.Inject

class TrendingReposViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    fun fetchTrendingRepos() : List<ApiEntry>{
        TODO("Not yet implemented")
    }

}