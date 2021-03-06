package com.example.tmsproject2.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmsproject2.R
import com.example.tmsproject2.music.Artist

class ArtistsAdapter(private  val artists: List<Artist>, private val artistSelected: (Artist)-> Unit)
    : RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item, parent, false),
            artistSelected
        )
    }

    override fun onBindViewHolder(holder: ArtistsAdapter.ViewHolder, position: Int) {
        holder.setArtist(artists[position])
    }

    override fun getItemCount(): Int = artists.size

    class ViewHolder(private  val view: View, private val artistSelected: (Artist) -> Unit): RecyclerView.ViewHolder(view){
        private var image: AppCompatImageView = view.findViewById(R.id.image_artist)
        private var title: AppCompatTextView = view.findViewById(R.id.text_artist_title)
        private var artist: Artist? = null

        init {
            view.setOnClickListener{
                artist?.let {
                    artistSelected(it)
                }
            }
        }

        fun setArtist(artist: Artist) {
            Glide.with(view.context)
                .load(artist.image.last().text)
                .into(image)

            title.text = artist.name
        }
    }
}