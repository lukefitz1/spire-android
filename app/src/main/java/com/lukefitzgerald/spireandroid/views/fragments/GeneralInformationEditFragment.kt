package com.lukefitzgerald.spireandroid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.views.models.GeneralInformationListViewModel
import java.util.*

private const val TAG = "GeneralInfoEditFrag"
private const val ARG_GI_ID = "gi_id"

class GeneralInformationEditFragment : Fragment() {

    private lateinit var generalInformation: GeneralInformation

    private lateinit var generalInfoLabel: EditText
    private lateinit var generalInfo: EditText
    private lateinit var createNewGeneralInfo: Button

    private lateinit var label1: TextView
    private lateinit var label2: TextView

    private val generalInformationListViewModel: GeneralInformationListViewModel by lazy {
        ViewModelProviders.of(this).get(GeneralInformationListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val giId: UUID = arguments?.getSerializable(ARG_GI_ID) as UUID
        Log.d(TAG, "GI ID: ${giId}")

        generalInformationListViewModel.loadGeneralInformation(giId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_general_information_edit, container, false)

//        generalInfoLabel = view.findViewById(R.id.general_information_label_text_input)
//        generalInfo = view.findViewById(R.id.general_information_text_input)
//        createNewGeneralInfo = view.findViewById(R.id.create_general_info_btn)

        label1 = view.findViewById(R.id.label1)
        label2 = view.findViewById(R.id.label2)

//        createNewGeneralInfo.setOnClickListener {
//            Log.d(TAG, "GI Label: ${generalInfoLabel.text}")
//            Log.d(TAG, "GI Info: ${generalInfo.text}")
//
//            val gi = GeneralInformation()
//            gi.informationLabel = "${generalInfoLabel.text}"
//            gi.information = "${generalInfo.text}"
//            generalInformationListViewModel.addGeneralInformation(gi)
////            callbacks?.onGeneralInformationSelected(gi.id)
//
//            this.activity!!.supportFragmentManager.popBackStack()
//        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generalInformationListViewModel.giLiveData.observe(
            viewLifecycleOwner,
            Observer { gi ->
                gi?.let {
                    this.generalInformation = gi
                    updateUI()
                }
            }
        )
    }

    private fun updateUI() {
        label1.setText(generalInformation.informationLabel)
        label2.setText(generalInformation.information)
    }

    companion object {
        fun newInstance(giId: UUID): GeneralInformationEditFragment {
            val args = Bundle().apply {
                putSerializable(ARG_GI_ID, giId)
            }
            return GeneralInformationEditFragment().apply {
                arguments = args
            }
        }
    }
}