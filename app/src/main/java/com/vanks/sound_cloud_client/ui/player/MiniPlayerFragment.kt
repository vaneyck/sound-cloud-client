package com.vanks.sound_cloud_client.ui.player

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.vanks.sound_cloud_client.databinding.FragmentMiniPlayerBinding
import com.vanks.sound_cloud_client.util.Reusable
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.util.SOUND_CLOUD_CLIENT_ID
import com.vanks.sound_cloud_client.util.buildMediaSource
import com.vanks.sound_cloud_client.viewmodel.Track
import kotlinx.android.synthetic.main.fragment_mini_player.view.*


class MiniPlayerFragment : Fragment() {

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    lateinit var binding: FragmentMiniPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mini_player, container, false)
        val root = binding.root

        var playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        var musicRepository = Reusable.musicRepository

        playerViewModel.track = musicRepository.retrieveCurrentTrack()
        playerViewModel.track.observe(this, Observer {
            binding.track = it
            playTrack(it)
        })
        return root
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun initializePlayer() {
        if (Reusable.player == null) {
            Reusable.player = ExoPlayerFactory.newSimpleInstance(this.context)
        }

        var playerView = binding.root.playbackControlView as PlayerControlView
        playerView.setPlayer(Reusable.player)
        // show the controls indefinitely
        playerView.showTimeoutMs = -1

        // Do not play when resuming to activity
        //Reusable.player.setPlayWhenReady(playWhenReady)

        // Do not set the player head to current position when going back
        //Reusable.player.seekTo(currentWindow, playbackPosition)
    }

    fun playTrack(track: Track) {
        val uri = Uri.parse(track.streamUrl)
        val mediaSource = buildMediaSource(uri, this.context)
        Reusable.player.prepare(mediaSource, true, false)
        Reusable.player.setPlayWhenReady(playWhenReady)
    }

    /**
     * hideSystemUi is a helper method called in onResume which allows us to have a full screen experience:
     */
    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
//        playerView.setSystemUiVisibility(
//            View.SYSTEM_UI_FLAG_LOW_PROFILE
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        )
    }

    private fun releasePlayer() {
        if (Reusable.player != null) {
            playWhenReady = Reusable.player.playWhenReady
            playbackPosition = Reusable.player.currentPosition
            currentWindow = Reusable.player.currentWindowIndex
            Log.i("Player", "Releasing the player")
            Reusable.player.release()
            // player = null
        }
    }
}