package com.example.dolananlist.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dolananlist.R
import com.example.dolananlist.core.data.local.entity.WishlistEntity
import com.example.dolananlist.databinding.GameItemBinding
import com.example.dolananlist.gamewishlist.domain.model.Wishlist

class WishlistAdapter(
    private val wishlist: List<Wishlist>,
    private val onItemClicked: (Wishlist) -> Unit
) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameItemBinding.bind(itemView)
        fun bind(wish: Wishlist) {
            with(binding) {
                Glide.with(itemView)
                    .load(wish.backgroundImage)
                    .into(imgGame)
                tvName.text = wish.name
                tvPlatform.text = wish.platforms
                tvGenre.text = wish.genres
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishlist[position]
        holder.bind(wish)
        holder.itemView.setOnClickListener { onItemClicked(wish) }
    }

    override fun getItemCount(): Int = wishlist.size
}