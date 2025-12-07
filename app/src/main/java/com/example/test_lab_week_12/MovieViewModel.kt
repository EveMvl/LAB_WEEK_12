package com.example.test_lab_week_12

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.test_lab_week_12.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<List<Movie>>(emptyList())
    val popularMovies: StateFlow<List<Movie>> = _popularMovies

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error

    init {
        loadMovies()
    }

    private fun loadMovies() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR).toString()

        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMovies()

                // ⭐ Assignment Step: FILTER & SORT
                .map { list ->
                    list
                        .filter { movie ->
                            movie.releaseDate?.startsWith(currentYear) == true
                        }
                        .sortedByDescending { it.popularity }
                }

                .catch { e ->
                    _error.value = "An error occurred: ${e.message}"
                }

                .collect { filteredList ->
                    _popularMovies.value = filteredList
                }
        }
    }

    class Factory(private val repository: MovieRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }
}
