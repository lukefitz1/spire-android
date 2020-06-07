package com.lukefitzgerald.spireandroid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.views.models.GeneralInformationListViewModel

private const val TAG = "GeneralInfoCreateFrag"

class GeneralInformationCreateFragment : Fragment() {

    private lateinit var generalInfoLabel: EditText
    private lateinit var generalInfo: EditText
    private lateinit var createNewGeneralInfo: Button

    private val generalInformationListViewModel: GeneralInformationListViewModel by lazy {
        ViewModelProviders.of(this).get(GeneralInformationListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Opening the create GI fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_general_information_create, container, false)

        generalInfoLabel = view.findViewById(R.id.general_information_label_text_input)
        generalInfo = view.findViewById(R.id.general_information_text_input)
        createNewGeneralInfo = view.findViewById(R.id.create_general_info_btn)

        createNewGeneralInfo.setOnClickListener {
            Log.d(TAG, "GI Label: ${generalInfoLabel.text}")
            Log.d(TAG, "GI Info: ${generalInfo.text}")

            val gi = GeneralInformation()
            gi.informationLabel = "${generalInfoLabel.text}"
            gi.information = "${generalInfo.text}"
            generalInformationListViewModel.addGeneralInformation(gi)
//            callbacks?.onGeneralInformationSelected(gi.id)
        }

        return view
    }

    companion object {
        fun newInstance(): GeneralInformationCreateFragment {
            return GeneralInformationCreateFragment()
        }
    }
}