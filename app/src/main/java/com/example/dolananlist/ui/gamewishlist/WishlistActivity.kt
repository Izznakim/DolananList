package com.example.dolananlist.ui.gamewishlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.data.Result
import com.example.dolananlist.data.local.entity.WishlistEntity
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.ui.WishlistAdapter
import com.example.dolananlist.ui.detailgame.DetailActivity
import com.example.dolananlist.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val wishlistViewModel: WishlistViewModel by viewModels { factory }

        supportActionBar?.title = "Wishlist"

        with(binding) {
            rvGame.layoutManager = LinearLayoutManager(this@WishlistActivity)
            rvGame.setHasFixedSize(true)

            wishlistViewModel.getWishlist().observe(this@WishlistActivity) {
                when (it) {
                    is Result.Loading -> progressBar.visibility = View.VISIBLE
                    is Result.Success -> {
                        progressBar.visibility = View.GONE
                        val wishlist = it.data
                        rvGame.adapter = setWishlist(wishlist)
                    }
                    is Result.Error -> {
                        progressBar.visibility = View.GONE
                        Snackbar.make(
                            window.decorView.rootView,
                            "Terjadi Kesalahan ${it.error}",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setWishlist(wishlist: List<WishlistEntity>): WishlistAdapter {
        val listWish = ArrayList<WishlistEntity>()
        for (game in wishlist) {
            listWish.add(game)
        }
        return WishlistAdapter(wishlist) {
            startActivity(
                Intent(this, DetailActivity::class.java).also { intent ->
                    intent.putExtra(DetailActivity.GAME_DETAIL, it.id)
                }
            )
        }
    }
}