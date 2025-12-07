package com.example.test_lab_week_12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_lab_week_12.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = mutableListOf<Movie>()

    fun addMovies(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.movie_poster)
        private val title: TextView = itemView.findViewById(R.id.movie_title)

        fun bind(movie: Movie) {
            title.text = movie.title ?: "-"

            val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"

            Glide.with(itemView.context)
                .load(posterUrl)
                .into(poster)
        }
    }
}
