package com.lukefitzgerald.spireandroid.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.Artwork
import java.util.*

@Dao
interface ArtworkDao {

    @Query("SELECT * FROM artwork")
    fun getArtworks(): List<Artwork>

    @Query("SELECT * FROM artwork WHERE id=(:id)")
    fun getArtwork(id: UUID): Artwork?

    @Query("SELECT * FROM artwork WHERE collectionId=(:collectionId)")
    fun getArtworkForCollection(collectionId: UUID): List<Artwork>
}
