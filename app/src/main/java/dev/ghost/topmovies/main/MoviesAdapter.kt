package dev.ghost.topmovies.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.ghost.topmovies.R
import dev.ghost.topmovies.databinding.ItemMovieBinding
import dev.ghost.topmovies.entities.Movie

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var allMovies: MutableList<Movie> = mutableListOf()

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie;
            binding.executePendingBindings();
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieBinding: ItemMovieBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_movie, parent, false
            )
        return MovieViewHolder(movieBinding)
    }

    override fun getItemCount() = allMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = allMovies[position]
        holder.bind(currentMovie)
    }

    internal fun setMovies(currentMovies: List<Movie>) {
        allMovies.clear()
        allMovies.addAll(currentMovies)
        notifyDataSetChanged()
    }
}