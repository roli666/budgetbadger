package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.dagger.AppComponent
import com.example.budgetbadger.dagger.DaggerAppComponent
import com.example.budgetbadger.databinding.MovieDetailFragmentBinding
import com.example.budgetbadger.repositories.MovieRepository
import com.example.budgetbadger.services.MovieDetailViewModelFactory
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment(private val movieId: Int) : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding
    private val applicationGraph: AppComponent = DaggerAppComponent.create()

    @Inject
    lateinit var repo: MovieRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(layoutInflater)
        applicationGraph.inject(this)
        viewModel = requireActivity().run {
            ViewModelProvider(this, MovieDetailViewModelFactory(repo, movieId)).get(
                movieId.toString(),
                MovieDetailViewModel::class.java
            )
        }

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.movieDescription.text = it.description
            binding.movieTitle.text = it.title
            Glide.with(this).load(BuildConfig.IMAGE_BASE_URL + it.poster_path)
                .into(binding.movieImage)
        })

        return binding.root
    }
}