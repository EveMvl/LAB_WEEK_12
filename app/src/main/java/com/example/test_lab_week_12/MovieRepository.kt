package com.example.test_lab_week_12

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test_lab_week_12.api.MovieService
import com.example.test_lab_week_12.model.Movie
import com.example.test_lab_week_12.api.PopularMoviesResponse

class MovieRepository(private val movieService: MovieService) {

    private val apiKey = "51c3a089a391b11ad3971258840aadc4"

    private val movieLiveData = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = movieLiveData

    private val errorLiveData = MutableLiveData<String>()
    val error: LiveData<String> get() = errorLiveData

    suspend fun fetchMovies() {
        try {
            // pastikan kita pakai tipe fully-qualified agar tidak salah import
            val response: PopularMoviesResponse = movieService.PopularMovies(apiKey)

            // debug: log type & fields
            Log.d("MovieRepository", "Got response: ${response::class.java.name} page=${response.page}")

            // set data
            movieLiveData.postValue(response.results)
        } catch (exception: Exception) {
            Log.e("MovieRepository", "fetchMovies error", exception)
            errorLiveData.postValue("An error occurred: ${exception.message}")
        }
    }
}
