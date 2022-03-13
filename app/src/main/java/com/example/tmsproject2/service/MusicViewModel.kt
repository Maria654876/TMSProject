package com.example.tmsproject2.service

import androidx.lifecycle.*
import com.example.tmsproject2.music.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel: ViewModel(), LifecycleEventObserver {

    companion object {
        private const val TAG = "MusicViewHolder"
    }

    private var artistsService: ArtistsService? = null

    val artistsLiveData = MutableLiveData<List<Artist>>()

    fun setArtistService(service: ArtistsService) {
        this.artistsService = service
    }



    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                println("ON_CREATE")
                getArtists()
            }

        }
    }

    private fun getArtists() {
        viewModelScope.launch(Dispatchers.IO) {
            val artists = artistsService?.getTopArtists()?.artists?.artist ?: listOf()
            artistsLiveData.postValue(artists)
        }
    }
}