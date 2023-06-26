package com.example.githubtrends.domain.repository

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.presentation.model.UiApiEntry
import com.example.githubtrends.presentation.model.UiItem

interface Repository  {
    suspend fun fetchTrendingRepos() : Resource<UiApiEntry>
}