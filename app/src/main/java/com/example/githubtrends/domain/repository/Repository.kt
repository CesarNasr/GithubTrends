package com.example.githubtrends.domain.repository

import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.presentation.model.UiApiEntry

interface Repository  {
    suspend fun fetchTrendingRepos() : Resource<UiApiEntry>
}