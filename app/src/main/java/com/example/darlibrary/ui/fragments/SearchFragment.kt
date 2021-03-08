package com.example.darlibrary.ui.fragments

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.darlibrary.R
import com.example.darlibrary.ui.Resource
import com.example.darlibrary.ui.SearchViewModel
import com.example.darlibrary.ui.adapter.BookAdapter
import com.example.darlibrary.utils.textChanges
import kotlinx.android.synthetic.main.fragment_search.booksRv
import kotlinx.android.synthetic.main.fragment_search.filterBooksBtn
import kotlinx.android.synthetic.main.fragment_search.searchProgressBar
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var bookAdapter: BookAdapter
    private lateinit var popupMenu: PopupMenu


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
                bookAdapter.filter.filter(it)
            }
            .launchIn(lifecycleScope)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initFilter()
        observeViewModel()
    }

    private fun initFilter() {
        searchViewModel.getGenres()
        popupMenu = PopupMenu(context, filterBooksBtn).apply {
            menu.add(Menu.NONE, 0, 0, "All")
            setOnMenuItemClickListener {
                bookAdapter.filterByGenre(it.itemId)
                true
            }
        }

        filterBooksBtn.setOnClickListener {
            popupMenu.show()
        }

    }

    private fun initRecycler() {
        bookAdapter = BookAdapter()
        booksRv.adapter = bookAdapter
        booksRv.layoutManager = GridLayoutManager(context, 2)
    }

    private fun observeViewModel() {
        searchViewModel.books.observe(viewLifecycleOwner, {
            bookAdapter.setOriginalList(it)
        })

        searchViewModel.remoteBookState.observe(viewLifecycleOwner, { state ->
            searchProgressBar.isVisible = state is Resource.Loading
            if (state is Resource.Error) {
                showError(state.message)
            }
        })

        searchViewModel.remoteGenreState.observe(viewLifecycleOwner, {
            popupMenu.menu.apply {
                for (genre in it) {
                    add(Menu.NONE,genre.id, genre.id, genre.title)
                }
            }
        })

    }

    private fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}