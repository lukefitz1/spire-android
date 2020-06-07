package com.lukefitzgerald.spireandroid.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.views.models.GeneralInformationListViewModel
import java.util.*

private const val TAG = "GeneralInfoListFragment"

class GeneralInformationListFragment : Fragment() {

    //Required interface for hosting activities
    interface Callbacks {
        fun onGeneralInformationSelected(generalInformationId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var generalInfoRecyclerView: RecyclerView
    private var adapter: GeneralInformationAdapter? = GeneralInformationAdapter(emptyList())

    private val generalInformationListViewModel: GeneralInformationListViewModel by lazy {
        ViewModelProviders.of(this).get(GeneralInformationListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_general_information_list, container, false)

        generalInfoRecyclerView = view.findViewById(R.id.general_information_recycler_view) as RecyclerView
        generalInfoRecyclerView.layoutManager = LinearLayoutManager(context)
        generalInfoRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generalInformationListViewModel.generalInformationListLiveData.observe(
            viewLifecycleOwner,
            Observer { generalInformations ->
                generalInformations?.let {
                    Log.i(TAG, "Got general infos ${generalInformations.size}")
                    updateUI(generalInformations)
                }
            }
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_general_information_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_general_information -> {

                val fragment = GeneralInformationCreateFragment.newInstance()
                this.activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(generalInformations: List<GeneralInformation>) {
        adapter = GeneralInformationAdapter(generalInformations)
        generalInfoRecyclerView.adapter = adapter
    }

    private inner class GeneralInformationHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var generalInfo : GeneralInformation

        val generalInfoTextView: TextView = itemView.findViewById(R.id.general_information_label_text_input)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(generalInfo: GeneralInformation) {
            this.generalInfo = generalInfo
            generalInfoTextView.text = this.generalInfo.informationLabel
        }

        override fun onClick(v: View) {
//            Toast.makeText(context, "${this.generalInfo.informationLabel} pressed!", Toast.LENGTH_SHORT).show()

            val fragment = GeneralInformationEditFragment.newInstance(this.generalInfo.id)
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()

//            callbacks?.onGeneralInformationSelected(generalInfo.id)
        }
    }

    private inner class GeneralInformationAdapter(var generalInfo: List<GeneralInformation>) : RecyclerView.Adapter<GeneralInformationListFragment.GeneralInformationHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): GeneralInformationHolder {
            val view = layoutInflater.inflate(R.layout.list_item_general_information, parent, false)
            return GeneralInformationHolder(view)
        }

        override fun getItemCount() = generalInfo.size

        override fun onBindViewHolder(holder: GeneralInformationHolder, position: Int) {
            val gi = generalInfo[position]

            holder.bind(gi)
        }
    }
}