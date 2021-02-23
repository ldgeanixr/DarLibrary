package com.example.darlibrary.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.darlibrary.R
import com.example.darlibrary.ui.HomeViewModel
import com.example.darlibrary.ui.adapter.BookAdapter
import kotlinx.android.synthetic.main.fragment_home.booksRv
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment() : Fragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var bookAdapter: BookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        bookAdapter = BookAdapter()
        booksRv.adapter = bookAdapter
        booksRv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeViewModel() {
        homeViewModel.books.observe(viewLifecycleOwner, bookAdapter::submitList)
    }
}