package com.vanks.sound_cloud_client.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.databinding.SingleAlbumBinding
import com.vanks.sound_cloud_client.domainmodel.Album

class AlbumAdapter(val navController: NavController) : RecyclerView.Adapter<AlbumViewHolder>() {

    var albums: ArrayList<Album> = ArrayList()

    fun setData(items: ArrayList<Album>) {
        albums = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleAlbumBinding>(
            inflater,
            R.layout.single_album,
            parent,
            false
        )
        return AlbumViewHolder(binding)
    }

    override fun getItemCount() = albums.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = albums[position]
        holder.binding.albumImage.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                navController.navigate(R.id.navigation_collection_view)
            }

        })
    }

}

class AlbumViewHolder(val binding: SingleAlbumBinding) :
    RecyclerView.ViewHolder(binding.getRoot())