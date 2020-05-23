package com.lukefitzgerald.spireandroid.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import java.util.*

@Dao
interface GeneralInformationDao {

    @Query("SELECT * FROM generalInformation")
    fun getGeneralInformations(): List<GeneralInformation>

    @Query("SELECT * FROM generalInformation WHERE id=(:id)")
    fun getGeneralInformation(id: UUID): GeneralInformation?
}