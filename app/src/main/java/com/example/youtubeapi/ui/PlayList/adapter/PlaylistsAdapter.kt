package com.example.youtubeapi.ui.PlayList.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.remote.model.Item
import com.example.youtubeapi.utils.loadImage


class PlaylistsAdapter(val onClick: (Item) -> Unit) : RecyclerView.Adapter<PlaylistsAdapter.PlaylistsViewHolder>(){

    private val playlists = arrayListOf<Item>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistsViewHolder {
        return PlaylistsViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PlaylistsViewHolder, position: Int) {
        holder.onBind(playlists[position])
    }

    override fun getItemCount(): Int {
        return playlists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPlaylists(items : List<Item>){
        playlists.addAll(items)
        notifyDataSetChanged()
    }

    inner class PlaylistsViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(playlists: Item) {
            binding.ivItem.loadImage(playlists.snippet.thumbnails.medium.url)
            binding.tvTitle.text = playlists.snippet.title
            binding.tvVideo.text = "${playlists.contentDetails.itemCount} video series"
            itemView.setOnClickListener {
                onClick(playlists)
            }
        }

    }
}
