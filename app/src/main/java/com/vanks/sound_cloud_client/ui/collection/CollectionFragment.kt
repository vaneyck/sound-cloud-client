package com.vanks.sound_cloud_client.ui.notifications

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.material.button.MaterialButton
import com.vanks.sound_cloud_client.R
import com.vanks.sound_cloud_client.adapter.TrackAdapter
import com.vanks.sound_cloud_client.databinding.FragmentCollectionViewBinding
import com.vanks.sound_cloud_client.repository.MusicRepository
import com.vanks.sound_cloud_client.ui.collection.CollectionViewModel
import com.vanks.sound_cloud_client.util.Reusable
import com.vanks.sound_cloud_client.util.buildMediaSource

class CollectionFragment : Fragment() {

    private lateinit var collectionViewModel: CollectionViewModel

    val args: CollectionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCollectionViewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_collection_view, container, false)
        val root = binding.root
        collectionViewModel = ViewModelProviders.of(this).get(CollectionViewModel::class.java)
        var musicRepository = Reusable.musicRepository

        collectionViewModel.trackHolder =
            musicRepository.getTrackHolder(args.collectionIndex, args.collectionType)
        collectionViewModel.trackCollection = musicRepository.getTracksForCollection()

        collectionViewModel.trackHolder.observe(this, Observer {
            binding.trackHolder = it
        })

        collectionViewModel.trackCollection.observe(this, Observer {
            binding.trackCollection = it
        })

        // Pull track information
        musicRepository.retrieveTracksForCollection(
            musicRepository.getResourceId(
                args.collectionIndex,
                args.collectionType
            ), args.collectionType
        )

        val collectionRecyclerView = root.findViewById<RecyclerView>(R.id.collectionRecyclerView)
        collectionRecyclerView.adapter = TrackAdapter(musicRepository)
        collectionRecyclerView.layoutManager =
            LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        root.findViewById<MaterialButton>(R.id.play_collection)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    var concatenatingMediaSource = ConcatenatingMediaSource()

                    binding.trackCollection?.tracks?.forEach {
                        concatenatingMediaSource.addMediaSource(
                            buildMediaSource(
                                Uri.parse(it.streamUrl),
                                root.context
                            )
                        )
                    }

                    Reusable.player.prepare(concatenatingMediaSource, true, false)
                }
            })

        return root
    }
}