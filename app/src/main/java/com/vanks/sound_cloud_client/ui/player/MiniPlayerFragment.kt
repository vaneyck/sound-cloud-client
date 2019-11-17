package com.vanks.sound_cloud_client.ui.player

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
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
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerControlView
import com.vanks.sound_cloud_client.databinding.FragmentMiniPlayerBinding
import com.vanks.sound_cloud_client.util.Reusable
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.util.buildMediaSource
import com.vanks.sound_cloud_client.viewmodel.Track
import kotlinx.android.synthetic.main.fragment_mini_player.view.*
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import android.content.Intent
import com.vanks.sound_cloud_client.MainActivity
import android.os.Build
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.concurrent.thread


class MiniPlayerFragment : Fragment() {

    val TAG = "MiniPlayerFragment"

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    lateinit var binding: FragmentMiniPlayerBinding
    lateinit var playerNotificationManager: PlayerNotificationManager

    var musicRepository = Reusable.musicRepository

    private val CHANNEL_ID = "sound-cloud-clone"
    private val NOTIFICATION_ID = 345

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_mini_player, container, false)
        val root = binding.root

        var playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)

        playerViewModel.track = musicRepository.retrieveCurrentTrack()
        playerViewModel.track.observe(this, Observer {
            binding.track = it
            playTrack(it)
        })

        // Setup notifications
        playerNotificationManager = PlayerNotificationManager(
            this.context,
            CHANNEL_ID,
            NOTIFICATION_ID,
            DescriptionAdapter(this.context!!)
        )
//        TODO : This doesnt work. Set notification color
//        playerNotificationManager.setColor(Color.BLACK)
//        playerNotificationManager.setColorized(true)


        root.findViewById<LinearLayout>(R.id.mini_player_container)
            .setOnClickListener {
                this.findNavController().navigate(R.id.maxPlayerFragment)
                musicRepository.setInPlayerMode(true)
            }

        return root
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "Resuming the app")
        createNotificationChannel()
        initializePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Destroying the app")
        //releasePlayer()
    }

    private fun initializePlayer() {
        Log.i(TAG, "initializePlayer: player is " + Reusable.player)
        if (Reusable.player == null) {
            Reusable.player = ExoPlayerFactory.newSimpleInstance(this.context)
        }

        var playerView = binding.root.playbackControlView as PlayerControlView
        playerView.setPlayer(Reusable.player)

        playerNotificationManager.setPlayer(Reusable.player)
        // show the controls indefinitely
        playerView.showTimeoutMs = -1

        // Do not play when resuming to activity
        //Reusable.player.setPlayWhenReady(playWhenReady)

        // Do not set the player head to current position when going back
        //Reusable.player.seekTo(currentWindow, playbackPosition)
    }

    private fun playTrack(track: Track) {
        Log.d(TAG, "playTrack: previous track ${musicRepository.previousTrack?.title}")
        Log.d(TAG, "playTrack: request to play track ${track.title}")

        if (track.id != musicRepository.previousTrack?.id) {
            Log.d(TAG, "playing new track ${track.title}")
            val uri = Uri.parse(track.streamUrl)
            val mediaSource = buildMediaSource(uri, this.context)
            Reusable.player.prepare(mediaSource, true, false)
            Reusable.player.setPlayWhenReady(playWhenReady)
        }
        musicRepository.previousTrack = track
    }

    private fun releasePlayer() {
        if (Reusable.player != null) {
            playWhenReady = Reusable.player.playWhenReady
            playbackPosition = Reusable.player.currentPosition
            currentWindow = Reusable.player.currentWindowIndex
            Log.i("Player", "Releasing the player")
            playerNotificationManager.setPlayer(null)
            Reusable.player.release()
            // player = null
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                this.context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // Got this code from here https://medium.com/google-exoplayer/playback-notifications-with-exoplayer-a2f1a18cf93b
    class DescriptionAdapter(private val context: Context) :
        PlayerNotificationManager.MediaDescriptionAdapter {

        var TAG = "DescriptionAdapter"

        var hashToBitmap: MutableMap<String, Bitmap> = HashMap()

        override fun getCurrentContentTitle(player: Player): String {
            return Reusable.musicRepository.retrieveCurrentTrack().value!!.title
        }

        override fun getCurrentContentText(player: Player): String? {
            return Reusable.musicRepository.retrieveCurrentTrack().value!!.artistName
        }

        override fun getCurrentLargeIcon(
            player: Player,
            callback: PlayerNotificationManager.BitmapCallback
        ): Bitmap? {
            var image: Bitmap?
            var url = Reusable.musicRepository.retrieveCurrentTrack().value!!.imageUrl
            if (hashToBitmap.get(getMd5(url)) == null) {
                image = null
                thread {
                    hashToBitmap[getMd5(url)] = Picasso.get().load(url).get()
                }
            } else {
                image = hashToBitmap.get(getMd5(url))
            }
            return image
        }

        override fun createCurrentContentIntent(player: Player): PendingIntent? {
            val intent = Intent(context, MainActivity::class.java)
            return PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        fun getMd5(str: String): String {
            return String.format(
                "%032x",
                BigInteger(
                    1,
                    MessageDigest.getInstance("MD5").digest(str.toByteArray(Charsets.UTF_8))
                )
            )
        }
    }
}

