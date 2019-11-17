package com.vanks.sound_cloud_client.ui.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.ui.PlayerControlView
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.databinding.FragmentMaxPlayerBinding
import com.vanks.sound_cloud_client.util.Reusable
import kotlinx.android.synthetic.main.fragment_max_player.view.*

class MaxPlayerFragment : Fragment() {

    lateinit var binding: FragmentMaxPlayerBinding
    lateinit var playerView: PlayerControlView
    var musicRepository = Reusable.musicRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_max_player, container, false)
        val root = binding.root

        var playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)

        playerViewModel.track = musicRepository.retrieveCurrentTrack()
        playerViewModel.track.observe(this, Observer {
            binding.track = it
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        musicRepository.setInPlayerMode(false)
    }

    private fun initializePlayer() {
        playerView = binding.root.maxPlaybackControlView as PlayerControlView
        playerView.setPlayer(Reusable.player)
        playerView.showTimeoutMs = -1
        hideSystemUi()
    }

    /**
     * hideSystemUi is a helper method called in onResume which allows us to have a full screen experience:
     */
    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        playerView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )
    }
}