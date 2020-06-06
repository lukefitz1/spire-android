package com.lukefitzgerald.spireandroid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.GeneralInformation
import com.lukefitzgerald.spireandroid.views.models.GeneralInformationListViewModel

private const val TAG = "GeneralInfListFragment"

class GeneralInformationListFragment : Fragment() {

    private lateinit var generalInfoRecyclerView: RecyclerView
    private var adapter: GeneralInformationAdapter? = null

    private val generalInformationListViewModel: GeneralInformationListViewModel by lazy {
        ViewModelProviders.of(this).get(GeneralInformationListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d(TAG, "Total GIs: ${generalInformationListViewModel.generalInfo.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_general_information_list, container, false)

        generalInfoRecyclerView = view.findViewById(R.id.general_information_recycler_view) as RecyclerView
        generalInfoRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_customer_list, menu)
    }

    private fun updateUI() {
        val generalInfos = generalInformationListViewModel.generalInfo
        adapter = GeneralInformationAdapter(generalInfos)
        generalInfoRecyclerView.adapter = adapter
    }

    // Toast.makeText(context, "${customer.firstName} pressed!", Toast.LENGTH_SHORT).show()
    private inner class GeneralInformationHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var generalInfo : GeneralInformation

        val generalInfoTextView: TextView = itemView.findViewById(R.id.general_information_label)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(generalInfo: GeneralInformation) {
            this.generalInfo = generalInfo
            generalInfoTextView.text = this.generalInfo.informationLabel
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${this.generalInfo.informationLabel} pressed!", Toast.LENGTH_SHORT).show()
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