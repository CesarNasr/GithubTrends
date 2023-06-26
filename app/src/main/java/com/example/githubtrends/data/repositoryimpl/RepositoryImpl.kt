package com.example.githubtrends.data.repositoryimpl

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.data.network.api.ApiService
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.model.UiApiEntry
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter,
    private val itemMapper: ItemMapper
) : Repository {

    override suspend fun fetchTrendingRepos(): Resource<UiApiEntry> {
        return responseConverter.responseToResults(apiService.fetchTrendingRepos(), itemMapper)
    }
}