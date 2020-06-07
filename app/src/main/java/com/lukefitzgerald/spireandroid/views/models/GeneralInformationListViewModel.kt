package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.repositories.GeneralInformationRepository

class GeneralInformationListViewModel : ViewModel() {

    val generalInfo = mutableListOf<GeneralInformation>()

//    init {
//        for (i in 0 until 20) {
//            val gi = GeneralInformation()
//            gi.informationLabel = "General Info $i"
//            generalInfo += gi
//        }
//    }

    private val generalInformationRepository = GeneralInformationRepository.get()
    val generalInformationListLiveData = generalInformationRepository.getGeneralInformations()

    fun addGeneralInformation(generalInfo: GeneralInformation) {
        generalInformationRepository.addGeneralInformation(generalInfo)
    }
}