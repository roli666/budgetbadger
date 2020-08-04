package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.databinding.MovieDetailFragmentBinding
import com.example.budgetbadger.services.MovieDetailViewModelFactory
import com.example.budgetbadger.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(layoutInflater)
        viewModel = requireActivity().run {
            ViewModelProvider(this, MovieDetailViewModelFactory(args.movieId)).get(
                args.movieId.toString(),
                MovieDetailViewModel::class.java
            )
        }

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            binding.movieDescription.text = it?.description
            binding.movieTitle.text = it?.title
            Glide.with(this).load(BuildConfig.IMAGE_BASE_URL + it?.poster_path)
                .into(binding.movieImage)
        })

        return binding.root
    }
}