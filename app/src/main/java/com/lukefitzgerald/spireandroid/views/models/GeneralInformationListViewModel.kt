package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.repositories.GeneralInformationRepository
import java.util.*

class GeneralInformationListViewModel : ViewModel() {

    val generalInfo = mutableListOf<GeneralInformation>()
    private val giIdLiveData = MutableLiveData<UUID>()
    private val giRepository = GeneralInformationRepository.get()

//    init {
//        for (i in 0 until 20) {
//            val gi = GeneralInformation()
//            gi.informationLabel = "General Info $i"
//            generalInfo += gi
//        }
//    }

    private val generalInformationRepository = GeneralInformationRepository.get()
    val generalInformationListLiveData = generalInformationRepository.getGeneralInformations()

    var giLiveData: LiveData<GeneralInformation?> =
        Transformations.switchMap(giIdLiveData) { giId ->
            giRepository.getGeneralInformation(giId)
        }

    fun loadGeneralInformation(giId: UUID) {
        giIdLiveData.value = giId
    }

    fun addGeneralInformation(generalInfo: GeneralInformation) {
        generalInformationRepository.addGeneralInformation(generalInfo)
    }
}