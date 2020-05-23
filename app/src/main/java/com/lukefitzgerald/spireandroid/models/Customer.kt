package com.lukefitzgerald.spireandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Customer(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var createdAt: String = "",
    var updatedAt: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var phone: String = "",
    var streetAddress: String = "",
    var city: String = "",
    var state: String = "",
    var zip: String = "",
    var referredBy: String = "",
    var projectNotes: String = "",
    var collections: String = ""
)