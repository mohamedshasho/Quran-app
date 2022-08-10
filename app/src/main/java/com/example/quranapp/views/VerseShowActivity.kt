package com.example.quranapp.views

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quranapp.adapters.VerseAdapter
import com.example.quranapp.databinding.ActivityVerseShowBinding
import com.example.quranapp.database.MyRoomDatabase
import com.example.quranapp.repo.DbRepository
import com.example.quranapp.viewsModel.VerseShowViewModel
import com.example.quranapp.viewsModel.VerseShowViewModelFactory

class VerseShowActivity : AppCompatActivity() {


    private lateinit var binding: ActivityVerseShowBinding


    private val database by lazy { MyRoomDatabase.getInstant(this) }
    private val repository by lazy { DbRepository.getInstant(database) }

    val viewModel: VerseShowViewModel by viewModels {
        VerseShowViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //todo change activity to mvvm
        binding = ActivityVerseShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                window.decorView.windowInsetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            else -> {
                window.statusBarColor = Color.GRAY
            }
        }

        val id = intent.extras?.get("id") as Int?
        val type = intent.extras?.get("type")
        val start = intent.extras?.get("start") as Int?
        val end = intent.extras?.get("end") as Int?


        val verseAdapter = VerseAdapter()
        binding.recyclerviewVerse.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = verseAdapter
        }

        verseAdapter.onItemClick = { id_fav ->
            viewModel.setFavorite(id_fav)
        }
        if (type == "verse") {
            viewModel.getVerses(id!!).observe(this, { verses ->
                verses?.let { verseAdapter.submitList(it) }
            })

        } else {
            viewModel.getJuz(start!!, end!!).observe(this, { verses ->
                verses?.let { verseAdapter.submitList(it) }
            })
        }
    }

}