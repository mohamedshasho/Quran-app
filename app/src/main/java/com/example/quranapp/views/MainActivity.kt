package com.example.quranapp.views

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.*
import androidx.activity.viewModels
import androidx.fragment.app.Fragment

import com.example.quranapp.R
import com.example.quranapp.database.MyRoomDatabase
import com.example.quranapp.databinding.ActivityMainBinding
import com.example.quranapp.repo.DbRepository
import com.example.quranapp.views.fragments.HomeFragment
import com.example.quranapp.views.fragments.QuranFragment
import com.example.quranapp.views.fragments.SettingFragment
import com.example.quranapp.viewsModel.MainViewModel
import com.example.quranapp.viewsModel.MainViewModelFactory

import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    companion object {
        private const val TAG_FRAGMENT_ONE = "fragment_one"
        private const val TAG_FRAGMENT_TWO = "fragment_two"
        private const val TAG_FRAGMENT_THREE = "fragment_three"

    }

   private val database by lazy { MyRoomDatabase.getInstant(this) }
   private val repository by lazy { DbRepository.getInstant(database) }

    val viewModel: MainViewModel by viewModels {
       MainViewModelFactory(repository)
   }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavBar.itemIconTintList = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.statusBarColor = Color.GRAY
        }


        //viewModel.loadDataFromNetwork()
        binding.progressBar.visibility = View.GONE

//        viewModel.getJuzsFromDb().observe(this, { juzs ->
//            Log.d("livedata main", juzs.size.toString())
//        })
//        viewModel.getSurasFromDb().observe(this, { suras ->
//
//            Log.d("livedata main", suras.size.toString())
//        })


        setFragment(HomeFragment(), TAG_FRAGMENT_ONE)
        binding.bottomNavBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.bar_home -> {
                    setFragment(HomeFragment(), TAG_FRAGMENT_ONE)
                    return@setOnItemSelectedListener true
                }
                R.id.bar_quran -> {
                    setFragment(QuranFragment(), TAG_FRAGMENT_TWO)
                    return@setOnItemSelectedListener true
                }
                R.id.bar_setting -> {
                    setFragment(SettingFragment(), TAG_FRAGMENT_THREE)
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }

        }


    }

    private fun setFragment(fragment: Fragment, tag: String) {
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            return
        }

        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.constaint_view_main, fragment, tag).commit()


    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy")
    }




}



