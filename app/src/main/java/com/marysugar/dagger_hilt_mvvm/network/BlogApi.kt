package com.marysugar.dagger_hilt_mvvm.network

import retrofit2.http.GET

interface BlogApi {

    @GET("blogs")
    suspend fun get(): List<BlogObjectResponse>
}