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
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListSharedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}