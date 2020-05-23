package com.lukefitzgerald.spireandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Artist(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var createdAt: String = "",
    var updatedAt: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var additionalInfo: String = "",
    var biography: String = ""
)