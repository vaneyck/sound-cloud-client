package com.vanks.sound_cloud_client.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.databinding.FragmentMiniPlayerBinding
import com.vanks.sound_cloud_client.repository.MusicRepository
import com.vanks.sound_cloud_client.util.Reusable

class MiniPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentMiniPlayerBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mini_player, container, false)
        val root = binding.root

        var playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        var musicRepository = Reusable.musicRepository

        playerViewModel.track = musicRepository.retrieveCurrentTrack()
        playerViewModel.track.observe(this, Observer {
            binding.track = it
        })
        return root
    }
}