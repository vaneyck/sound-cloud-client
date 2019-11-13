package com.vanks.sound_cloud_client.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.adapter.AlbumAdapter
import com.vanks.sound_cloud_client.adapter.PlaylistAdapter
import com.vanks.sound_cloud_client.adapter.TrackAdapter
import com.vanks.sound_cloud_client.collection.AlbumCollection
import com.vanks.sound_cloud_client.collection.PlaylistCollection
import com.vanks.sound_cloud_client.collection.TrackCollection
import com.vanks.sound_cloud_client.databinding.FragmentHomeBinding
import com.vanks.sound_cloud_client.repository.MusicRepository
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = binding.root

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        var musicRepository = MusicRepository()

        homeViewModel.albumCollection = musicRepository.getAlbumCollection()
        homeViewModel.trackCollection = musicRepository.getTrackCollection()
        homeViewModel.playlistCollection = musicRepository.getPlaylistCollection()

        // Observe live data for changes
        homeViewModel.trackCollection.observe(this, Observer {
            binding.trackCollection = it
        })
        homeViewModel.albumCollection.observe(this, Observer {
            binding.albumCollection = it
        })
        homeViewModel.playlistCollection.observe(this, Observer {
            binding.playlistCollection = it
        })

        val albumRecylerView = root.findViewById<RecyclerView>(R.id.album_recyclerview)
        val playlistRecyclerView = root.findViewById<RecyclerView>(R.id.playlist_recyclerview)
        val trackRecylerView = root.findViewById<RecyclerView>(R.id.track_recyclerview)

        val navController = this.findNavController()

        albumRecylerView.adapter = AlbumAdapter(navController)
        playlistRecyclerView.adapter = PlaylistAdapter(navController)
        trackRecylerView.adapter = TrackAdapter()

        // Set album and playlist to be horizontally aligned
        albumRecylerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        playlistRecyclerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        trackRecylerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        root.findViewById<MaterialButton>(R.id.search_button)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val searchTerm = search_field.text.toString().trim()
//                homeViewModel.albumCollection = musicRepository.getAlbumCollection()
//                homeViewModel.trackCollection = musicRepository.getTrackCollection()
//                homeViewModel.playlistCollection = musicRepository.getPlaylistCollection()
                    musicRepository.searchTracks(searchTerm)
                    musicRepository.searchPlaylists(searchTerm)
                }
            })

        return root
    }
}