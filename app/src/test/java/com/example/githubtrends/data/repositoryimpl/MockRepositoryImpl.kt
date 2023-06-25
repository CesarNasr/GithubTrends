package com.example.githubtrends.data.repositoryimpl

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.domain.repository.Repository


class MockRepositoryImpl : Repository {
    override suspend fun fetchTrendingRepos(): Resource<ApiEntry> {
        TODO("Not yet implemented")
    }

}