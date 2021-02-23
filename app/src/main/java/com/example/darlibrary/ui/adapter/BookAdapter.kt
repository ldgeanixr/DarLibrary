package com.example.darlibrary.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.darlibrary.R
import com.example.darlibrary.data.db.Book
import kotlinx.android.synthetic.main.book_item.view.bookId
import kotlinx.android.synthetic.main.book_item.view.bookName

class BookAdapter : ListAdapter<Book, BookAdapter.BookViewHolder>(diffUtil) {

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
        holder.itemView.bookId.text = item.id.toString()
        holder.itemView.bookName.text = item.name
    }
}