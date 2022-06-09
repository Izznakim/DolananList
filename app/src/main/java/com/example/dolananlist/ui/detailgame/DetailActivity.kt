package com.example.dolananlist.ui.detailgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dolananlist.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detail Page"
    }
}