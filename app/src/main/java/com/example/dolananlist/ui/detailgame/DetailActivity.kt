package com.example.dolananlist.ui.detailgame

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dolananlist.R
import com.example.dolananlist.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(GAME_DETAIL, 0)

        detailViewModel.getGameDetail(id)
        setupView()
    }

    private fun setupView() {
        detailViewModel.gameDetail.observe(this) {
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(it.backgroundImage)
                    .into(ivGame)
                tvName.text = it.name
                tvAltName.text = it.alternativeNames.joinToString { it }
                tvGenre.text = it.genres.joinToString { it.name }
                tvPlatform.text = it.platforms.joinToString { it.platform.name }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    tvDesc.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
                tvDesc.text = it.descriptionRaw
                tvRelease.text = resources.getString(R.string.release_date, it.released)
                tvDeveloper.text =
                    resources.getString(R.string.developer, it.developers.joinToString { it.name })
                tvPublisher.text =
                    resources.getString(R.string.publisher, it.publishers.joinToString { it.name })
                tvTag.text = resources.getString(R.string.tag, it.tags.joinToString { it.name })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val GAME_DETAIL = "game_detail"
    }
}