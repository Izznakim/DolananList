package com.example.dolananlist.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dolananlist.core.R
import com.example.dolananlist.core.data.remote.response.ResultsItem
import com.example.dolananlist.core.databinding.GameItemBinding

class GameAdapter(
    private val listData: List<ResultsItem>,
    private val onItemClicked: (ResultsItem) -> Unit
) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameItemBinding.bind(itemView)
        fun bind(data: ResultsItem) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.backgroundImage)
                    .into(imgGame)
                tvName.text = data.name
                tvPlatform.text = data.parentPlatforms.joinToString { it.platform.name }
                tvGenre.text = data.genres.joinToString { it.name }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
        holder.itemView.setOnClickListener { onItemClicked(data) }
    }

    override fun getItemCount(): Int = listData.size
}