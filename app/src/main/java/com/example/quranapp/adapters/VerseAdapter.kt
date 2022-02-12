package com.example.quranapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.R
import com.example.quranapp.databinding.VerseShowItemBinding
import com.example.quranapp.models.Verse

class VerseAdapter : ListAdapter<Verse, VerseAdapter.VerseHolder>(VerseComparator()) {
    var onItemClick: ((id: Int) -> Unit)? = null  //ط2

    class VerseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = VerseShowItemBinding.bind(itemView)

        companion object {
            fun create(parent: ViewGroup): VerseHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.verse_show_item, parent, false)
                return VerseHolder(view)
            }
        }

        fun bind(verse: Verse) {
            binding.textviewVerseItemText.text = verse.text
            if (verse.favorite == 1)
                binding.imageViewVerseItemFavorite.setImageResource(R.drawable.ic_bookmark_fill)
            else
                binding.imageViewVerseItemFavorite.setImageResource(R.drawable.ic_bookmark_nfill)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseHolder {
        return VerseHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VerseHolder, position: Int) {
        holder.bind(getItem(position))

        holder.binding.imageViewVerseItemFavorite.setOnClickListener {
            onItemClick?.invoke(getItem(position).id)  // ط2
        }
    }

    class VerseComparator : DiffUtil.ItemCallback<Verse>() {
        override fun areItemsTheSame(oldItem: Verse, newItem: Verse): Boolean {
            Log.d("areItemsTheSame",(oldItem.id == newItem.id).toString() )
            return oldItem.id == newItem.id
            // === compare between reference
        }

        override fun areContentsTheSame(oldItem: Verse, newItem: Verse): Boolean {
            Log.d("areContentsTheSame",(oldItem.favorite== newItem.favorite).toString() )
            return oldItem.favorite == newItem.favorite
        }

    }
}