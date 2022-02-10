package com.example.quranapp.views.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.views.VerseShowActivity
import com.example.quranapp.adapters.SuraAdapter
import com.example.quranapp.databinding.FragmentSuraBinding
import com.example.quranapp.views.MainActivity


class SuraFragment : Fragment() {

    lateinit var binding: FragmentSuraBinding
    lateinit var suraAdapter: SuraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        suraAdapter = SuraAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = (activity as MainActivity).viewModel;
        binding = FragmentSuraBinding.inflate(layoutInflater, container, false)

        binding.recyclerviewSura.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = suraAdapter
        }

        viewModel.getSurasFromDb().observe(viewLifecycleOwner,{suras->
            suras?.let {
                suraAdapter.submitList(it)
            }

        })
        suraAdapter.onItemClick = { id ->
            Toast.makeText(activity, id.toString(), Toast.LENGTH_SHORT).show()
            startActivity(Intent(context, VerseShowActivity::class.java).apply {
                putExtra("id", id)
                putExtra("type", "verse")
            })
        }

        return binding.root
    }

}