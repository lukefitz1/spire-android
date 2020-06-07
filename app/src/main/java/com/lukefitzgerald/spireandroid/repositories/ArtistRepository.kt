package com.lukefitzgerald.spireandroid.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lukefitzgerald.spireandroid.database.SpireDatabase
import com.lukefitzgerald.spireandroid.models.Artist
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "spire-database"

class ArtistRepository private constructor(context: Context) {

    private val database : SpireDatabase = Room.databaseBuilder(
        context.applicationContext,
        SpireDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val artistDao = database.artistDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getArtists(): LiveData<List<Artist>> = artistDao.getArtists()

    fun getArtist(id: UUID): LiveData<Artist?> = artistDao.getArtist(id)

    fun addArtist(artist: Artist) {
        executor.execute {
            artistDao.addArtist(artist)
        }
    }

    companion object {
        private var INSTANCE: ArtistRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ArtistRepository(context)
            }
        }

        fun get(): ArtistRepository {
            return INSTANCE ?: throw IllegalStateException("ArtistRepository must be initialized")
        }
    }
}