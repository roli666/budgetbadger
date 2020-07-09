package com.example.budgetbadger.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.budgetbadger.viewmodels.MovieDetailViewModel
import com.example.budgetbadger.R
import com.example.budgetbadger.databinding.ListFragmentBinding
import com.example.budgetbadger.databinding.MovieDetailFragmentBinding
import com.example.budgetbadger.viewmodels.MovieListSharedViewModel
import dagger.android.support.DaggerFragment

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() =
            MovieDetailFragment()
    }

    private lateinit var viewModel: MovieListSharedViewModel
    private lateinit var binding: MovieDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailFragmentBinding.inflate(layoutInflater)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MovieListSharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding.movieDescription.text = viewModel.selected.value?.description
        binding.movieTitle.text = viewModel.selected.value?.title
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}