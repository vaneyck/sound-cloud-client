package com.vanks.sound_cloud_client.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.adapter.UserAdapter
import com.vanks.sound_cloud_client.adapter.PlaylistAdapter
import com.vanks.sound_cloud_client.adapter.TrackAdapter
import com.vanks.sound_cloud_client.databinding.FragmentHomeBinding
import com.vanks.sound_cloud_client.util.Reusable
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.Log
import android.view.KeyEvent
import com.google.android.material.textfield.TextInputEditText


class HomeFragment : Fragment() {

    val TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel
    var musicRepository = Reusable.musicRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = binding.root

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        homeViewModel.userCollection = musicRepository.getAlbumCollection()
        homeViewModel.trackCollection = musicRepository.getTrackCollection()
        homeViewModel.playlistCollection = musicRepository.getPlaylistCollection()

        // Observe live data for changes
        homeViewModel.trackCollection.observe(this, Observer {
            binding.trackCollection = it
        })
        homeViewModel.userCollection.observe(this, Observer {
            binding.userCollection = it
        })
        homeViewModel.playlistCollection.observe(this, Observer {
            binding.playlistCollection = it
        })

        val albumRecylerView = root.findViewById<RecyclerView>(R.id.album_recyclerview)
        val playlistRecyclerView = root.findViewById<RecyclerView>(R.id.playlist_recyclerview)
        val trackRecylerView = root.findViewById<RecyclerView>(R.id.track_recyclerview)

        val navController = this.findNavController()

        albumRecylerView.adapter = UserAdapter(navController)
        playlistRecyclerView.adapter = PlaylistAdapter(navController)
        trackRecylerView.adapter = TrackAdapter(musicRepository)

        // Set album and playlist to be horizontally aligned
        albumRecylerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        playlistRecyclerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        trackRecylerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        root.findViewById<TextInputEditText>(R.id.search_field)
            .setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    val searchTerm = search_field.text.toString().trim()
                    searchByTerm(searchTerm)
                    hideKeyboard()
                }
                true
            }
        return root
    }

    private fun searchByTerm(searchTerm: String) {
        musicRepository.searchTracks(searchTerm)
        musicRepository.searchPlaylists(searchTerm)
        musicRepository.searchUsers(searchTerm)
    }

    private fun hideKeyboard() {
        try {
            val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(activity?.getCurrentFocus()?.getWindowToken(), 0)
        } catch (e: Exception) {
            Log.e(TAG, "Could not hide keyboard")
        }
    }
}