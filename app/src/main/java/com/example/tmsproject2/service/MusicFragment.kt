package com.example.tmsproject2.service

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmsproject2.R

class MusicFragment: Fragment() {

    private val viewModel by viewModels<MusicViewModel>()
    private lateinit var recyclerArtists: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lastFMNetwork = LastFMNetworkImpl.getInstance() as LastFMNetwork
        viewModel.setArtistService(lastFMNetwork.getArtistsService())
        lifecycle.addObserver(viewModel)

        recyclerArtists = view.findViewById(R.id.recyclerView)
        recyclerArtists.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setListeners()
        subscribeToLiveData()
    }

    private fun setListeners() {

    }

    private fun subscribeToLiveData() {
        viewModel.artistsLiveData.observe(viewLifecycleOwner, { artists ->
            recyclerArtists.adapter = ArtistsAdapter(artists) { artist ->
                println(artist)
            }
        })
    }
}