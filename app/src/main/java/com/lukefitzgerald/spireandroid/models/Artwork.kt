package com.lukefitzgerald.spireandroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Artwork(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var createdAt: String = "",
    var updatedAt: String = "",
    var objectId: String = "",
    var artType: String = "",
    var title: String = "",
    var date: String = "",
    var image: String = "",
    var description: String = "",
    var dimensions: String = "",
    var frameDimensions: String = "",
    var condition: String = "",
    var currentLocation: String = "",
    var source: String = "",
    var dateAcquired: String = "",
    var amountPaid: String = "",
    var currentValue: String = "",
    var notes: String = "",
    var notesImage: String = "",
    var additionalInfoLabel: String = "",
    var additionalInfoText: String = "",
    var additionalInfoImage: String = "",
    var reviewedBy: String = "",
    var reviewedDate: String = "",
    var provenance: String = "",
    var dateAcquiredLabel: String = "",
    var collectionId: String = "",          // change to UUID
    var customerId: String = "",            // change to UUID
    var notesImageTwo: String = "",
    var additionalInfoImageTwo: String = "",
    var showGeneralInfo: String = "",
    var customTitle: String = "",
    var artistIds: String = "",             // change to string array
    var generalInfoIds: String = ""         // change to string array
)