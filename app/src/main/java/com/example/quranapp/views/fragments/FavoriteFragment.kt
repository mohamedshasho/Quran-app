package com.example.quranapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quranapp.adapters.VerseAdapter
import com.example.quranapp.databinding.FragmentFavoriteBinding
import com.example.quranapp.views.MainActivity


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val viewModel = (activity as MainActivity).viewModel

        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        val verseAdapter = VerseAdapter()

        binding.recyclerviewVerseFavorite.apply {
            adapter = verseAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        viewModel.getFavoriteVerse().observe(viewLifecycleOwner, {
            it?.let { verseAdapter.submitList(it) }
        })


        verseAdapter.onItemClick = { id_fav ->
            viewModel.setFavorite(id_fav)
        }

        return binding.root
    }

}