package com.example.dolananlist.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dolananlist.databinding.ActivityMainBinding
import com.example.dolananlist.detailgame.DetailActivity
import com.example.dolananlist.core.ui.WishlistAdapter
import com.example.dolananlist.core.domain.model.Game
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class WishlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wishlistViewModel: WishlistViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Wishlist"

        loadKoinModules(wishlistModule)

        with(binding) {
            progressBar.visibility = View.GONE
            rvGame.layoutManager = LinearLayoutManager(this@WishlistActivity)
            rvGame.setHasFixedSize(true)

            wishlistViewModel.wishlist.observe(this@WishlistActivity) {
                rvGame.adapter = setWishlist(it)
            }
        }
    }

    private fun setWishlist(wishlist: List<Game>): WishlistAdapter {
        val listWish = ArrayList<Game>()
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