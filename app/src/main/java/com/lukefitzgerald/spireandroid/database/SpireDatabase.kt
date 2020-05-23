package com.lukefitzgerald.spireandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.lukefitzgerald.spireandroid.database.dao.*

import com.lukefitzgerald.spireandroid.models.Customer
import com.lukefitzgerald.spireandroid.models.Collection
import com.lukefitzgerald.spireandroid.models.Artwork
import com.lukefitzgerald.spireandroid.models.Artist
import com.lukefitzgerald.spireandroid.models.GeneralInformation

@Database(entities = [Customer::class, Collection::class, Artwork::class, Artist::class, GeneralInformation::class], version=1)
@TypeConverters(SpireTypeConverters::class)
abstract class SpireDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun collectionDao(): CollectionDao
    abstract fun artworkDao(): ArtworkDao
    abstract fun artistDao(): ArtistDao
    abstract fun generalInformationDao(): GeneralInformationDao
}