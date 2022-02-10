package com.example.quranapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.R
import com.example.quranapp.databinding.ItemTabSuraBinding
import com.example.quranapp.models.Sura


class  SuraAdapter : ListAdapter<Sura, SuraAdapter.SuraHolder>(SurasComparator()) {

    var onItemClick: ((id: Int) -> Unit)? = null

    class SuraHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemTabSuraBinding.bind(itemView)

        companion object {
            fun create(parent: ViewGroup): SuraHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_tab_sura, parent, false)
                return SuraHolder(view)
            }
        }

        fun bind(sura: Sura) {
            binding.textViewNameSura.text = sura.name
            binding.textViewNameSuraArabic.text = sura.nameArabic
            binding.textviewNumber.text = "${sura.id}."
            //todo edit text format
            binding.textViewTransNameSura.text =
                "${sura.translatedName?.name} (${sura.versesCount.toString()})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraHolder {
        return SuraHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SuraHolder, position: Int) {
        holder.bind(getItem(position))
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(getItem(position).id)
        }
    }

    class SurasComparator : DiffUtil.ItemCallback<Sura>() {
        override fun areItemsTheSame(oldItem: Sura, newItem: Sura): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Sura, newItem: Sura): Boolean {
            return oldItem.id == newItem.id
        }
    }
}