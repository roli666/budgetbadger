package com.example.budgetbadger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.example.budgetbadger.databinding.ActivityMainBinding
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.fragments.MovieDetailFragment
import com.example.budgetbadger.fragments.MovieListFragment

class MainActivity : AppCompatActivity(), MovieListFragment.OnMovieTapListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is MovieListFragment) {
            fragment.setOnMovieSelectedListener(this)
        }
    }
    override fun onMovieSelected(movie: Movie) {
        val newFragment = MovieDetailFragment(movie.id)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, newFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}