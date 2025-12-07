package com.example.test_lab_week_12.api

import retrofit2.http.GET
import retrofit2.http.Query

import com.example.test_lab_week_12.model.Movie
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>
)
