package com.lukefitzgerald.spireandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GeneralInformation(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var createdAt: String = "",
    var updatedAt: String = "",
    var informationLabel: String = "",
    var information: String = ""
)