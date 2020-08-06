package com.example.budgetbadger

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.budgetbadger.databinding.ActivityMainBinding
import com.example.budgetbadger.model.Movie
import com.example.budgetbadger.fragments.MovieListFragment
import com.example.budgetbadger.fragments.MovieListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieTapListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onMovieSelected(movie: Movie) {
        val action =
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie.id)
        findNavController(R.id.nav_host_fragment).navigate(action)
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}