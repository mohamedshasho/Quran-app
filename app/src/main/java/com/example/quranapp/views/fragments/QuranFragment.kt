package com.example.quranapp.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quranapp.adapters.PagerAdapter
import com.example.quranapp.databinding.FragmentQuranBinding
import com.google.android.material.tabs.TabLayoutMediator

class QuranFragment : Fragment() {
    companion object {
        val tabs = arrayOf("Sura", "Juz")
    }
    lateinit var binding: FragmentQuranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentQuranBinding.inflate(layoutInflater, container, false)
        val fragments = ArrayList<Fragment>()


        fragments.add(SuraFragment())
        fragments.add(JuzFragment())
        setPageAdapterFragments(fragments)

        return binding.root
    }


    private  fun setPageAdapterFragments(fragments: ArrayList<Fragment>){
        val pagerAdapter = PagerAdapter(requireActivity(), fragments)

        binding.viewPager2.adapter = pagerAdapter
        TabLayoutMediator(
            binding.tabLayoutQuran,
            binding.viewPager2,
        ) { tab, posision ->
            tab.text = tabs[posision]
        }.attach()
    }
}