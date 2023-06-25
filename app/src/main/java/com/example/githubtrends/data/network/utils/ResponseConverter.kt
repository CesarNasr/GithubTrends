package com.example.githubtrends.data.network.utils

import com.example.githubtrends.data.model.response.ApiEntry
import retrofit2.Response

/**
 * Responsible for converting api response to resource class
 */
class ResponseConverter {

    fun responseToResults(response: Response<ApiEntry>): Resource<ApiEntry> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        response.errorBody()?.let {
            return Resource.Error(
                response.code(),
                response.message()
            )
        }
        return Resource.Error(
            response.code(),
            response.message()
        )
    }

}