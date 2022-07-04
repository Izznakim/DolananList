package com.example.dolananlist.listgame

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.R
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.core.ui.GameAdapter
import com.example.dolananlist.detailgame.DetailActivity
import com.example.dolananlist.detailgame.DetailActivity.Companion.GAME_ID
import com.example.dolananlist.gamewishlist.WishlistActivity

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

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.wishlist -> {
                Intent(this, WishlistActivity::class.java).also {
                    startActivity(it)
                }
                true
            }
            else -> true
        }
    }

    private fun setListGame(games: List<ResultsItem>) {
        val listGame = ArrayList<ResultsItem>()
        for (game in games) {
            listGame.add(game)
        }
        val gameAdapter = GameAdapter(listGame) {
            startActivity(
                Intent(this, DetailActivity::class.java).also { intent ->
                    intent.putExtra(GAME_ID, it.id)
                }
            )
        }
        binding.rvGame.adapter = gameAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}