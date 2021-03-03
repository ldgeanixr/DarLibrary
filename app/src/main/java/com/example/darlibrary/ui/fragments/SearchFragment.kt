package com.example.darlibrary.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.darlibrary.R
import com.example.darlibrary.ui.HomeViewModel
import com.example.darlibrary.ui.Resource
import com.example.darlibrary.ui.SearchViewModel
import com.example.darlibrary.ui.adapter.BookAdapter
import com.example.darlibrary.utils.textChanges
import kotlinx.android.synthetic.main.fragment_search.booksRv
import kotlinx.android.synthetic.main.fragment_search.searchProgressBar
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.coroutineContext

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val homeViewModel: SearchViewModel by viewModel()
    private lateinit var bookAdapter: BookAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.itemSearch)
        val searchView : SearchView = searchItem?.actionView as SearchView

        searchView.textChanges()
            .debounce(500)
            .onEach {
                Log.d("TAG", "onCreateOptionsMenu: TextSUBMITTED " + it)
                bookAdapter.filter.filter(it)
            }
            .launchIn(lifecycleScope)

//        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                bookAdapter.filter.filter(newText)
//                return true
//            }
//
//        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        bookAdapter = BookAdapter()
        booksRv.adapter = bookAdapter
        booksRv.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeViewModel() {
        homeViewModel.books.observe(viewLifecycleOwner, {
            bookAdapter.setOriginalList(it)
        })

        homeViewModel.remoteBookState.observe(viewLifecycleOwner, Observer { state ->
            searchProgressBar.isVisible = state is Resource.Loading
            if (state is Resource.Error) {
                showError(state.message)
            }
        })

    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}