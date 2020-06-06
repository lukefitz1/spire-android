package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.Artist

class ArtistListViewModel : ViewModel() {

    val artists = mutableListOf<Artist>()

    init {
        for (i in 0 until 20) {
            val artist = Artist()
            artist.firstName = "First$i"
            artist.lastName = "Last$i"
            artists += artist
        }
    }
}