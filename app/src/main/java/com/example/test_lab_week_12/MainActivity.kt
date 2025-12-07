package com.example.test_lab_week_12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            repeatOnLifecycle(androidx.lifecycle.Lifecycle.State.STARTED) {

                // ---- Collect movies ----
                launch {
                    movieVM.popularMovies.collect { list ->
                        movieAdapter.addMovies(list)
                    }
                }

                // ---- Collect errors ----
                launch {
                    movieVM.error.collect { msg ->
                        if (msg.isNotEmpty()) {
                            Snackbar.make(recyclerView, msg, Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }
}
