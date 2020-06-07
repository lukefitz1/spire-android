package com.lukefitzgerald.spireandroid

import android.app.Application
import com.lukefitzgerald.spireandroid.repositories.ArtistRepository
import com.lukefitzgerald.spireandroid.repositories.CustomerRepository
import com.lukefitzgerald.spireandroid.repositories.GeneralInformationRepository

class SpireApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CustomerRepository.initialize(this)
        GeneralInformationRepository.initialize(this)
        ArtistRepository.initialize(this)
    }
}