package com.example.dolananlist.listgame

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.R
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.Game
import com.example.dolananlist.core.ui.GameAdapter
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.detailgame.DetailActivity
import com.example.dolananlist.detailgame.DetailActivity.Companion.GAME_ID
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        binding.rvGame.layoutManager = LinearLayoutManager(this)
        binding.rvGame.setHasFixedSize(true)

        mainViewModel.getGameList().observe(this) {
            if (it!=null){
                when(it){
                    is ApiResponse.Loading->showLoading(true)
                    is ApiResponse.Success->{
                        showLoading(false)
                        val game=it.data
                        setListGame(game)
                    }
                    is ApiResponse.Error->{
                        showLoading(false)
                        Snackbar.make(window.decorView,it.errorMessage,Snackbar.LENGTH_SHORT).show()
                    }
                    is ApiResponse.Empty->{
                        showLoading(false)
                        Snackbar.make(window.decorView,"Data is Empty",Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
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
                val uri= Uri.parse("dolananlist://wishlist")
                Intent(Intent.ACTION_VIEW,uri).also {
                    startActivity(it)
                }
                true
            }
            else -> true
        }
    }

    private fun setListGame(games: List<Game>) {
        val listGame = ArrayList<Game>()
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