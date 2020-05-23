package com.lukefitzgerald.spireandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Collection(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var createdAt: String = "",
    var updatedAt: String = "",
    var collectionName: String = "",
    var identifier: String = "",
    var year: String = "",
    var phone: String = "",
    var customerId: String = "",
    var artworks: String = ""
)