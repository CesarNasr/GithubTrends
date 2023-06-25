package com.example.githubtrends.domain.repository

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.Resource

interface Repository  {
    suspend fun fetchTrendingRepos() : Resource<ApiEntry>
}