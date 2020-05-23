package com.lukefitzgerald.spireandroid.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.Collection
import java.util.*

@Dao
interface CollectionDao {

    @Query("SELECT * FROM collection")
    fun getCollections(): List<Collection>

    @Query("SELECT * FROM collection WHERE id=(:id)")
    fun getCollection(id: UUID): Collection?

    @Query("SELECT * FROM collection WHERE customerId=(:customerId)")
    fun getCollectionsForCustomer(customerId: UUID): List<Collection>
}