package com.example.dolananlist.gamewishlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.core.ui.WishlistAdapter
import com.example.dolananlist.detailgame.DetailActivity
import com.example.dolananlist.core.ui.ViewModelFactory
import com.example.dolananlist.gamewishlist.domain.model.Wishlist

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
            progressBar.visibility = View.GONE
            rvGame.layoutManager = LinearLayoutManager(this@WishlistActivity)
            rvGame.setHasFixedSize(true)

            wishlistViewModel.wishlist.observe(this@WishlistActivity) {
                rvGame.adapter = setWishlist(it)
            }
        }
    }

    private fun setWishlist(wishlist: List<Wishlist>): WishlistAdapter {
        val listWish = ArrayList<Wishlist>()
        for (game in wishlist) {
            listWish.add(game)
        }
        return WishlistAdapter(wishlist) {
            startActivity(
                Intent(this, DetailActivity::class.java).also { intent ->
                    intent.putExtra(DetailActivity.GAME_ID, it.id)
                }
            )
        }
    }
}