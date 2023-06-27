package com.example.githubtrends.data.repositoryimpl

import com.example.githubtrends.data.network.api.ApiService
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.model.UiApiEntry
import javax.inject.Inject

/**
 * Repository class that will communicate with the actual data source,
 * in more complicated use-cases, a repository might communicate with a
 * local database, remote database, files or any other data-storing class,
 *
 * It can also make decision from where to fetch data and how often
 * */
class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val responseConverter: ResponseConverter,
    private val itemMapper: ItemMapper
) : Repository {

    override suspend fun fetchTrendingRepos(): Resource<UiApiEntry> {
        return responseConverter.responseToResults(apiService.fetchTrendingRepos(), itemMapper)
    }
}