package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.Artist
import com.lukefitzgerald.spireandroid.repositories.ArtistRepository

class ArtistListViewModel : ViewModel() {

    val artists = mutableListOf<Artist>()

//    init {
//        for (i in 0 until 20) {
//            val artist = Artist()
//            artist.firstName = "First$i"
//            artist.lastName = "Last$i"
//            artists += artist
//        }
//    }

    private val artistRepository = ArtistRepository.get()
    val artistListLiveData = artistRepository.getArtists()

    fun addArtist(artist: Artist) {
        artistRepository.addArtist(artist)
    }
}