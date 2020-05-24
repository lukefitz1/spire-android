package com.lukefitzgerald.spireandroid

import android.app.Application
import com.lukefitzgerald.spireandroid.repositories.CustomerRepository

class SpireApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CustomerRepository.initialize(this)
    }
}