package com.example.test_lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.movie_list)
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter

        val repo = (application as MovieApplication).movieRepository
        val movieVM = ViewModelProvider(this, MovieViewModel.Factory(repo))
            .get(MovieViewModel::class.java)

        movieVM.popularMovies.observe(this) { list ->
            movieAdapter.addMovies(list)
        }

        movieVM.error.observe(this) { error ->
            if (!error.isNullOrBlank()) {
                Snackbar.make(recyclerView, error, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
