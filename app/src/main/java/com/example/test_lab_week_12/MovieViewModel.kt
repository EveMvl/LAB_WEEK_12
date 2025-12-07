package com.example.test_lab_week_12

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        loadMovies()
    }

    val popularMovies = repository.movies
    val error = repository.error

    private fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchMovies()
        }
    }

    class Factory(private val repository: MovieRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }
}
