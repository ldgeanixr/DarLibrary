package com.example.darlibrary.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.darlibrary.R
import com.example.darlibrary.data.db.Book
import kotlinx.android.synthetic.main.book_item.view.bookAuthor
import kotlinx.android.synthetic.main.book_item.view.bookTitle

class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(diffUtil), Filterable {

    private var originalList: List<Book> = ArrayList()

    fun setOriginalList(newList: List<Book>){
        originalList = newList
        submitList(originalList)
    }

    fun filterByGenre(genreId: Int){
        if (genreId == 0){
            submitList(originalList)
        }else{
            var filteredList = originalList.filter {
                it.genreId == genreId
            }
            submitList(filteredList)
        }
    }

    private val bookFilter = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val filteredList = ArrayList<Book>()

            if (constraint == null || constraint.isEmpty()){
                filteredList.addAll(originalList)
            }else{
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (book in originalList){
                    if (book.title.toLowerCase().contains(filterPattern)){
                        filteredList.add(book)
                    }
                }
            }

            return FilterResults().apply {
                values = filteredList
            }
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values != null){
                val newList = results?.values as List<Book>
                submitList(newList)
            }
        }

    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.bookTitle.text = item.title
        holder.itemView.bookAuthor.text = item.author
    }

    override fun getFilter(): Filter {
        return bookFilter
    }
}