package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.budgetbadger.BuildConfig
import com.example.budgetbadger.databinding.FragmentMovieDetailBinding
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)

        viewModel.fetchMovie(args.movieId)

        viewModel.movie.observe(viewLifecycleOwner, {
            binding.movieDescription.text = it?.description
            binding.movieTitle.text = it?.title
            Glide.with(this).load(BuildConfig.IMAGE_BASE_URL + it?.poster_path)
                .into(binding.movieImage)
        })

        return binding.root
    }
}
