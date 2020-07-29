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

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel =ViewModelProvider(this)
            .get(MainActivityViewModel::class.java)

        recyclerViewMainMovies.adapter = mainActivityViewModel.moviesAdapter
        recyclerViewMainMovies.layoutManager = LinearLayoutManager(this)

        mainActivityViewModel.loadingState.observe(this, Observer {
            val statusMessage = when (it.status) {
                Status.RUNNING -> getString(R.string.text_loading)
                Status.SUCCESS -> getString(R.string.text_loaded)
                Status.FAILED -> it.message
            }
            Toast.makeText(this, statusMessage, Toast.LENGTH_SHORT).show()
        })

        mainActivityViewModel.moviesData.observe(this, Observer {
            mainActivityViewModel.moviesAdapter.setMovies(it)
        })
    }
}