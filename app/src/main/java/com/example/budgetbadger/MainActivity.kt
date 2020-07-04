package com.example.budgetbadger

import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.transition.Transition
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
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                TODO("use query to get values")
            }
        }
        setContentView(binding.root)
    }

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is MovieListFragment) {
            fragment.setOnMovieSelectedListener(this)
        }
    }

    override fun onMovieSelected(movie: Movie) {
        val newFragment = MovieDetailFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, newFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

}