package com.example.githubtrends.data.repositoryimpl

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.domain.repository.Repository
import com.google.gson.Gson
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileReader


class MockRepositoryImpl(private val responseConverter: ResponseConverter) : Repository {

    override suspend fun fetchTrendingRepos(): Resource<ApiEntry> {
        return responseConverter.responseToResults(fetchMockJsonResponse())
    }

    private fun fetchMockJsonResponse(): Response<ApiEntry> {
        val path = "/assets/mock_response.json"
        val bufferedReader = BufferedReader(FileReader(path))

        val gson = Gson()

        val apiEntry = gson.fromJson(bufferedReader, ApiEntry::class.java)
        return Response.success(apiEntry)
    }
}