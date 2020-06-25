package com.example.budgetbadger.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbadger.R
import com.example.budgetbadger.adapters.MovieItemAdapter
import com.example.budgetbadger.databinding.ListFragmentBinding
import com.example.budgetbadger.entities.Movie
import com.example.budgetbadger.viewmodels.MovieListSharedViewModel
import com.example.budgetbadger.helpers.BitmapHelper

class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListSharedViewModel
    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(layoutInflater)
        binding.movieList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MovieItemAdapter(
                listOf(
                    Movie(
                        "Batman",
                        "Batman and the very long description",
                        BitmapHelper.makeEmptyColoredBitmap(300, 200, 0xffff0000),
                        6.9f,
                        100000
                    )
                )
            )
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieListSharedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}