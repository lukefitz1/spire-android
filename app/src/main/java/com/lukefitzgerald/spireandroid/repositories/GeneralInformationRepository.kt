package com.lukefitzgerald.spireandroid.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lukefitzgerald.spireandroid.database.SpireDatabase
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "spire-database"

class GeneralInformationRepository private constructor(context: Context) {

    private val database : SpireDatabase = Room.databaseBuilder(
        context.applicationContext,
        SpireDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val generalInformationDao = database.generalInformationDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getGeneralInformations(): LiveData<List<GeneralInformation>> = generalInformationDao.getGeneralInformations()

    fun getGeneralInformation(id: UUID): LiveData<GeneralInformation?> = generalInformationDao.getGeneralInformation(id)

    fun addGeneralInformation(generalInformation: GeneralInformation) {
        executor.execute {
            generalInformationDao.addGeneralInformation(generalInformation)
        }
    }

    companion object {
        private var INSTANCE: GeneralInformationRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = GeneralInformationRepository(context)
            }
        }

        fun get(): GeneralInformationRepository {
            return INSTANCE ?: throw IllegalStateException("GeneralInformationRepository must be initialized")
        }
    }
}