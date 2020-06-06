package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.GeneralInformation

class GeneralInformationListViewModel : ViewModel() {

    val generalInfo = mutableListOf<GeneralInformation>()

    init {
        for (i in 0 until 20) {
            val gi = GeneralInformation()
            gi.informationLabel = "General Info $i"
            generalInfo += gi
        }
    }
}