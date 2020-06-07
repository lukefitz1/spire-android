package com.lukefitzgerald.spireandroid.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.Customer
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import java.util.*

@Dao
interface GeneralInformationDao {

    @Query("SELECT * FROM generalInformation")
//    fun getGeneralInformations(): List<GeneralInformation>
    fun getGeneralInformations(): LiveData<List<GeneralInformation>>

    @Query("SELECT * FROM generalInformation WHERE id=(:id)")
//    fun getGeneralInformation(id: UUID): GeneralInformation?
    fun getGeneralInformation(id: UUID): LiveData<GeneralInformation?>

    @Insert
    fun addGeneralInformation(generalInformation: GeneralInformation)
}