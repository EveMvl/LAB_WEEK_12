package com.example.test_lab_week_12.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun PopularMovies(
        @Query("api_key") apiKey: String
    ): PopularMoviesResponse
}
