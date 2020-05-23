package com.lukefitzgerald.spireandroid.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.Artist
import java.util.*

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist")
    fun getArtists(): List<Artist>

    @Query("SELECT * FROM artist WHERE id=(:id)")
    fun getArtist(id: UUID): Artist?
}