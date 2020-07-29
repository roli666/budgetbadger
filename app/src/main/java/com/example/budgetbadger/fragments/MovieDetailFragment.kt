package com.example.budgetbadger.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbadger.databinding.MovieDetailFragmentBinding
import com.example.budgetbadger.repositories.MovieRepository
import com.example.budgetbadger.services.MovieDetailViewModelFactory
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment(private val movieId: Int) : Fragment() {

    companion object {
        fun newInstance(movieId: Int) =
            MovieDetailFragment(movieId)
    }

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    @Inject
    lateinit var repo: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(layoutInflater)

        viewModel = requireActivity().run {
            ViewModelProvider(this, MovieDetailViewModelFactory(repo, movieId)).get(
                MovieDetailViewModel::class.java
            )
        }

        binding.movieDescription.text = viewModel.movie.value?.description
        binding.movieTitle.text = viewModel.movie.value?.title

        return binding.root
    }

}