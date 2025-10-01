package com.example.animeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.animeapp.model.Anime

class AnimeAdapter(private val animeList: List<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(animeList[position])
    }

    override fun getItemCount(): Int = animeList.size

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAnime: ImageView = itemView.findViewById(R.id.imgAnime)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvScore: TextView = itemView.findViewById(R.id.tvScore)

        fun bind(anime: Anime) {
            tvTitle.text = anime.title
            tvScore.text = "Ranking: ${anime.ranking}"

            Glide.with(itemView.context)
                .load(anime.image)
                .placeholder(R.drawable.placeholder)
                .into(imgAnime)
        }
    }
}
