package com.example.dolananlist.ui.detailgame

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.dolananlist.R
import com.example.dolananlist.databinding.ActivityDetailBinding
import com.example.dolananlist.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    private var isBeenHere: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        if (savedInstanceState != null) {
            val result = savedInstanceState.getBoolean(STATE_RESULT)
            isBeenHere = result
        }

        val id = intent.getIntExtra(GAME_DETAIL, 0)

        detailViewModel.getGameDetail(id)
        if (!isBeenHere) {
            detailViewModel.getGameDetail(id)
            isBeenHere = true
        }
        setupView()
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun setupView() {
        var wish = false
        with(binding) {
            detailViewModel.gameDetail.observe(this@DetailActivity) { game ->
                Glide.with(this@DetailActivity)
                    .load(game.backgroundImage)
                    .into(ivGame)
                tvName.text = game.name
                tvAltName.text = game.alternativeNames.joinToString { it }
                tvGenre.text = game.genres.joinToString { it.name }
                tvPlatform.text = game.platforms.joinToString { it.platform.name }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tvDesc.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
                tvDesc.text = game.descriptionRaw
                tvRelease.text = resources.getString(R.string.release_date, game.released)
                tvDeveloper.text =
                    resources.getString(
                        R.string.developer,
                        game.developers.joinToString { it.name })
                tvPublisher.text =
                    resources.getString(
                        R.string.publisher,
                        game.publishers.joinToString { it.name })
                tvTag.text = resources.getString(R.string.tag, game.tags.joinToString { it.name })

                detailViewModel.checkExistOrNot(game.id)
                detailViewModel.isWish.observe(this@DetailActivity) { isWish ->
                    if (isWish) {
                        wish = true
                        fabWishlist.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_sharp_wish_24,
                                null
                            )
                        )
                    } else {
                        wish = false
                        fabWishlist.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_sharp_wish_border_24,
                                null
                            )
                        )
                    }
                }
                fabWishlist.setOnClickListener {
                    wish = if (wish) {
                        detailViewModel.deleteGameFromWishlist(game)
                        fabWishlist.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_sharp_wish_border_24,
                                null
                            )
                        )
                        false
                    } else {
                        detailViewModel.setGameToWishlist(game)
                        fabWishlist.setImageDrawable(
                            ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_sharp_wish_24,
                                null
                            )
                        )
                        true
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_RESULT, isBeenHere)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.fabWishlist.visibility = View.VISIBLE
            binding.ivGame.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val GAME_DETAIL = "game_detail"
        const val STATE_RESULT = "state_result"
    }
}