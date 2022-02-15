package com.example.quranapp.views.fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope


import com.example.quranapp.databinding.FragmentHomeBinding
import com.example.quranapp.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.abs
import com.example.quranapp.views.*


class HomeFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var binding: FragmentHomeBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = (activity as MainActivity).viewModel

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.appbar.addOnOffsetChangedListener(this)

        viewModel.verseSura.observe(viewLifecycleOwner,  { verse_sura ->

            verse_sura?.let {
                binding.textviewHomeVerbDay.text=it.textVerse
                binding.textviewHomeSubVerbDay.text= "from ${it.nameSura}"
            }
        })

        lifecycleScope.launch(Dispatchers.IO) {
            while (true) {
                withContext(Dispatchers.Main) {
                    binding.textviewHomeDate.text =
                        DateFormat.format("EEEE, dd MMM", Calendar.getInstance().time).toString()
                    binding.collapsingToolbar.title =
                        DateFormat.format("hh:mm a", Calendar.getInstance().time).toString()
                }
                delay(200)
            }
        }
        return binding.root
    }


    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        Log.d("appbar", (abs(verticalOffset) - appBarLayout!!.totalScrollRange).toString())
        val s = (abs(verticalOffset) - appBarLayout.totalScrollRange) * -1


        val appbarHeightChanging = s.toFloat().div(appBarLayout.totalScrollRange)

        binding.relativeTools.alpha = appbarHeightChanging
        binding.relativelayoutLocation.alpha=appbarHeightChanging

        binding.textviewHomeDate.alpha = appbarHeightChanging
        binding.textviewHomeSubDate.alpha = appbarHeightChanging

        if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
            //  Collapsed
            Log.d("STATE", "Collapsed")
            val param = binding.linearVerbToBay.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, 5, 0, 0)
            binding.linearVerbToBay.layoutParams = param
            binding.relativeTools.visibility = View.GONE
            binding.textviewHomeDate.visibility = View.GONE
            binding.textviewHomeSubDate.visibility = View.GONE
        } else {
            //Expanded
            binding.relativeTools.visibility = View.VISIBLE
            val param = binding.linearVerbToBay.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(0, resources.getDimensionPixelOffset(R.dimen._40sdp), 0, 0)
            binding.linearVerbToBay.layoutParams = param
            binding.textviewHomeDate.visibility = View.VISIBLE
            binding.textviewHomeSubDate.visibility = View.VISIBLE
            Log.d("STATE", "Expanded")
        }
    }


}