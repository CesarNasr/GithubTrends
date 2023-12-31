package com.example.githubtrends.data.repositoryimpl

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.network.utils.Resource
import com.example.githubtrends.data.network.utils.ResponseConverter
import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.domain.repository.Repository
import com.example.githubtrends.presentation.model.UiApiEntry
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileReader


class MockRepositoryImpl(private val responseConverter: ResponseConverter, private val itemMapper: ItemMapper) : Repository {
    private var errorType = MockErrorType.None
    fun setShouldReturnNetworkError(errorType: MockErrorType) {
        this.errorType = errorType
    }


    override suspend fun fetchTrendingRepos(): Resource<UiApiEntry> {
        return when (errorType) {
            MockErrorType.None -> responseConverter.responseToResults(fetchMockJsonSuccessResponse(), itemMapper)
            MockErrorType.InternetError -> getMockInternetErrorResponse()
            MockErrorType.Others -> getMockErrorResponse()
        }
    }

    private fun fetchMockJsonSuccessResponse(): Response<ApiEntry> {
        val path = "/assets/mock_response.json"
        val bufferedReader = BufferedReader(FileReader(path))

        val gson = Gson()

        val apiEntry = gson.fromJson(bufferedReader, ApiEntry::class.java)
        return Response.success((apiEntry))
    }

    private fun getMockErrorResponse(): Resource<UiApiEntry> {
        return Resource.Error(errorCode = 500, message = "Internet Error", data = null)
    }

    private fun getMockInternetErrorResponse(): Resource<UiApiEntry> {
        throw HttpException(
            Response.error<ResponseBody>(
                500,
                ResponseBody.create("plain/text".toMediaTypeOrNull(), "some error content")
            )
        )
    }
}


enum class MockErrorType {
    None,
    InternetError,
    Others
}