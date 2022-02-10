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

        viewModel.verse.observe(viewLifecycleOwner,  { verse ->
            verse?.let { binding.textviewHomeVerbDay.text=it }
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
        if (s in 51..99) {
            binding.relativeTools.alpha = 0.5f
            binding.relativelayoutLocation.visibility = View.GONE

        }
        if (s in 99..200) {
            binding.relativeTools.alpha = 0.7f
            binding.relativelayoutLocation.visibility = View.VISIBLE
        }
        if (s > 200) {
            binding.relativeTools.alpha = 1.0f
            binding.relativelayoutLocation.visibility = View.VISIBLE
        }

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

//    private fun render(){
//        lifecycleScope.launchWhenCreated {
//            viewModel.state.collect {
//                when(it){
//                    is MainViewState.RandomVerse -> {
//                        it.verse.observe(viewLifecycleOwner,{
//                            binding.textviewHomeVerbDay.text=it
//                        })
//                    }
//                    is MainViewState.RandomVerseError ->binding.textviewHomeVerbDay.text=it.error
//                    is MainViewState.Idle ->binding.textviewHomeVerbDay.text="Idle"
//                }
//            }
//        }
//    }

}