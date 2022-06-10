package com.example.dolananlist.ui.listgame

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.ResultsItem
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.ui.GameAdapter
import com.example.dolananlist.ui.detailgame.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGame.layoutManager = LinearLayoutManager(this)
        binding.rvGame.setHasFixedSize(true)

        mainViewModel.listGame.observe(this) {
            setListGame(it)
        }
    }

    private fun setListGame(games: List<ResultsItem>) {
        val listGame = ArrayList<ResultsItem>()
        for (game in games) {
            listGame.add(game)
        }
        val gameAdapter = GameAdapter(listGame) {
            startActivity(
                Intent(this, DetailActivity::class.java)
            )
        }
        binding.rvGame.adapter = gameAdapter
    }
}