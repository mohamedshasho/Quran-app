package com.example.quranapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.R
import com.example.quranapp.databinding.JuzTabItemBinding
import com.example.quranapp.models.Juz

class JuzAdapter :
    ListAdapter<Juz, JuzAdapter.JuzHolder>(JuzComparator()) {

    var onItemClick: ((start: Int, end: Int) -> Unit)? = null


    class JuzHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = JuzTabItemBinding.bind(itemView)
        companion object{
            fun create(parent: ViewGroup): JuzHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.juz_tab_item, parent, false)
                return JuzHolder(view)
            }
        }
        fun bind(juz: Juz) {
            binding.textViewNumberJuz.text = "Juz ${juz.id}"
            binding.textViewNumberJuzArabic.text = "الجزء ${juz.id}"
            binding.textViewJuzNameVerse.text = "${juz.id}."
            //todo edit text format
            binding.textViewJuzNameVerseEnglish.text =
                "${juz.versesCount}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JuzHolder {
        return JuzHolder.create(parent)
    }

    override fun onBindViewHolder(holder: JuzHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(getItem(position).firstVerseId,getItem(position).lastVerseId)
        }
    }

    class JuzComparator : DiffUtil.ItemCallback<Juz>() {
        override fun areItemsTheSame(oldItem: Juz, newItem: Juz): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Juz, newItem: Juz): Boolean {
            return oldItem.versesCount == newItem.versesCount
        }
    }
}