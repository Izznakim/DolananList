package com.example.dolananlist.detailgame

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.dolananlist.R
import com.example.dolananlist.core.data.source.remote.retrofit.ApiResponse
import com.example.dolananlist.core.domain.model.GameDetail
import com.example.dolananlist.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    private var isBeenHere: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getBoolean(STATE_RESULT)
            isBeenHere = result
        }

        val id = intent.getIntExtra(GAME_ID, 0)

        if (!isBeenHere) {
            setupViewModel(id)
            isBeenHere = true
        }
        setupViewModel(id)
    }

    private fun setupViewModel(id:Int) {
        detailViewModel.getGameDetail(id).observe(this@DetailActivity) {
            Log.d("GameRepository", "setupViewModel: $it")
            if (it!=null) {
                when(it){
                    is ApiResponse.Loading->showLoading(true)
                    is ApiResponse.Success->{
                        showLoading(false)
                        val game=it.data
                        setupView(game)
                    }
                    is ApiResponse.Empty->{
                        showLoading(false)
                        Snackbar.make(window.decorView,"Data is Empty", Snackbar.LENGTH_SHORT).show()
                    }
                    is ApiResponse.Error->{
                        showLoading(false)
                        Snackbar.make(window.decorView,it.errorMessage,Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupView(game: GameDetail) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load(game.backgroundImage)
                .into(ivGame)
            tvName.text = game.name
            if (game.alternativeNames.isEmpty()) {
                tvAltName.visibility = View.GONE
            } else {
                tvAltName.text = game.alternativeNames.joinToString { it }
            }
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
            tvTag.text =
                resources.getString(R.string.tag, game.tags.joinToString { it.name })

            fabViewCondition(game)
        }
    }

    private fun fabViewCondition(game: GameDetail) {
        var wish = false
        with(binding) {
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_RESULT, isBeenHere)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.view.visibility = View.VISIBLE
            binding.fabWishlist.visibility = View.VISIBLE
            binding.ivGame.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val GAME_ID = "game_id"
        const val STATE_RESULT = "state_result"
    }
}