package com.example.quranapp.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.views.VerseShowActivity
import com.example.quranapp.adapters.JuzAdapter
import com.example.quranapp.databinding.FragmentSuraBinding
import com.example.quranapp.views.MainActivity

class JuzFragment : Fragment() {
    lateinit var binding: FragmentSuraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = (activity as MainActivity).viewModel

        binding = FragmentSuraBinding.inflate(layoutInflater, container, false)

        val juzAdapter = JuzAdapter()

        juzAdapter.onItemClick = { start, end ->
            startActivity(Intent(context, VerseShowActivity::class.java).apply {
                putExtra("type", "juz")
                putExtra("start", start)
                putExtra("end", end)
            })
        }
        viewModel.getJuzsFromDb().observe(viewLifecycleOwner, { juzs ->
            juzs?.let { juzAdapter.submitList(it) }
        })
        binding.recyclerviewSura.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

            adapter = juzAdapter
        }

        return binding.root
    }
}