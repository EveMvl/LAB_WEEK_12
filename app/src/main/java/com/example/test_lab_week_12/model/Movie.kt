package com.example.test_lab_week_12.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,

    @Json(name = "title")
    val title: String?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    @Json(name = "popularity")
    val popularity: Double
)
