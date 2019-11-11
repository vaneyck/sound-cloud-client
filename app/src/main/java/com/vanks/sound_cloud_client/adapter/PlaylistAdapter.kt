package com.vanks.sound_cloud_client.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.databinding.SinglePlaylistBinding
import com.vanks.sound_cloud_client.domainmodel.Playlist

class PlaylistAdapter() : RecyclerView.Adapter<PlaylistViewHolder>() {

    var playlists: ArrayList<Playlist> = ArrayList()

    fun setData(items: ArrayList<Playlist>) {
        playlists = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SinglePlaylistBinding>(
            inflater,
            R.layout.single_playlist,
            parent,
            false
        )
        return PlaylistViewHolder(binding)
    }

    override fun getItemCount() = playlists.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.binding.playlist = playlists[position]
    }

}

class PlaylistViewHolder(val binding: SinglePlaylistBinding) :
    RecyclerView.ViewHolder(binding.getRoot())