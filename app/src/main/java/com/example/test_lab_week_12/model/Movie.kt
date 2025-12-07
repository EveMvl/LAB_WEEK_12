package com.example.test_lab_week_12.model

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title: String,
    @Json(name = "release_date")
    val releaseDate: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    @Json(name = "poster_path")
    val posterPath: String? = null,
    @Json(name = "backdrop_path")
    val backdropPath: String? = null
)
