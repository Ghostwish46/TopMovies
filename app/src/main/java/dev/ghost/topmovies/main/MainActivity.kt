package dev.ghost.topmovies.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ghost.topmovies.R
import dev.ghost.topmovies.network.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "movie"
    }

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)

        observeLoadingStateData()
        observeMoviesData()
        initializeMoviesList()
    }

    // Observe live data connection states from data source.
    private fun observeLoadingStateData() {
        mainActivityViewModel.getLoadingStates().observe(this, Observer {
            when (it.status) {
                Status.RUNNING ->
                    Toast.makeText(this, getString(R.string.text_loading), Toast.LENGTH_SHORT)
                        .show()
                Status.SUCCESS ->
                    Toast.makeText(this, getString(R.string.text_loaded), Toast.LENGTH_SHORT).show()
                Status.FAILED ->
                    Toast.makeText(
                        this,
                        getString(R.string.text_error) + it.message,
                        Toast.LENGTH_LONG
                    ).show()
            }
        })
    }

    // Observe live data movies from view model.
    private fun observeMoviesData() {
        mainActivityViewModel.getMovies().observe(this, Observer {
            mainActivityViewModel.moviesAdapter.submitList(it)
        })
    }

    // Set adapter to the recycler view.
    private fun initializeMoviesList() {
        recyclerViewMainMovies.adapter = mainActivityViewModel.moviesAdapter
        recyclerViewMainMovies.layoutManager = LinearLayoutManager(this)
    }
}