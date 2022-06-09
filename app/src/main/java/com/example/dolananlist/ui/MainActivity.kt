package com.example.dolananlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.GameResponse
import com.example.dolananlist.ResultsItem
import com.example.dolananlist.databinding.ActivityMainBinding
import kotlin.math.log

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
            Log.d("MainActivity", "onCreate: ${it[0].name}")
        }
    }

    private fun setListGame(games: List<ResultsItem>) {
        val listGame = ArrayList<ResultsItem>()
        for (game in games) {
            listGame.add(game)
        }
        val gameAdapter = GameAdapter(listGame)
        binding.rvGame.adapter = gameAdapter
    }
}