package com.vanks.sound_cloud_client.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.adapter.TrackAdapter
import com.vanks.sound_cloud_client.databinding.FragmentCollectionViewBinding
import com.vanks.sound_cloud_client.ui.collection.CollectionViewModel
import com.vanks.sound_cloud_client.util.Resources

class CollectionFragment : Fragment() {

    private lateinit var collectionViewModel: CollectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCollectionViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_collection_view, container, false)
        val root = binding.root
        collectionViewModel = ViewModelProviders.of(this).get(CollectionViewModel::class.java)
        var musicRepository = Resources.musicRep

        collectionViewModel.trackHolder = musicRepository.getTrackHolder(1, "Playlist")

        collectionViewModel.trackHolder.observe(this, Observer {
            binding.trackHolder = it
        })

        val collectionRecyclerView = root.findViewById<RecyclerView>(R.id.collectionRecyclerView)
        collectionRecyclerView.adapter = TrackAdapter()
        collectionRecyclerView.layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        return root
    }
}