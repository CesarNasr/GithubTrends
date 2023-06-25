package com.example.githubtrends.data.network.api

import com.example.githubtrends.data.model.response.ApiEntry
import retrofit2.Response
import retrofit2.http.*


/**
 * Api Service class calling @GET , @POST,  @DELETE, @UPDATE, @EDIT etc ...
 */
interface ApiService {
    @GET("search/repositories?q=language=+sort:stars")
    suspend fun fetchTrendingRepos(): Response<ApiEntry>
}
