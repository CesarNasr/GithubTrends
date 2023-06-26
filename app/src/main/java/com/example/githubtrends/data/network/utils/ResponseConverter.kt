package com.example.githubtrends.data.network.utils

import com.example.githubtrends.data.model.response.ApiEntry
import com.example.githubtrends.data.utils.ItemMapper
import com.example.githubtrends.presentation.model.UiApiEntry
import retrofit2.Response

/**
 * Responsible for converting api response to resource class
 */
class ResponseConverter {

    fun responseToResults(response: Response<ApiEntry>, mapper: ItemMapper): Resource<UiApiEntry> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(mapper.mapToEntity(it))
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