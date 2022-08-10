package com.example.quranapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(private val fragmentActivity: FragmentActivity, val fragments: ArrayList<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}