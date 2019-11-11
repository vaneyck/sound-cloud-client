package com.vanks.sound_cloud_client.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.databinding.SingleTrackBinding
import com.vanks.sound_cloud_client.domainmodel.Track

class TrackAdapter() : RecyclerView.Adapter<TrackViewHolder>() {

    var tracks: ArrayList<Track> = ArrayList()

    fun setData(items: ArrayList<Track>) {
        tracks = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SingleTrackBinding>(
            inflater,
            R.layout.single_track,
            parent,
            false
        )
        return TrackViewHolder(binding)
    }

    override fun getItemCount() = tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.binding.track = tracks[position]
    }

}

class TrackViewHolder(val binding: SingleTrackBinding) :
    RecyclerView.ViewHolder(binding.getRoot())