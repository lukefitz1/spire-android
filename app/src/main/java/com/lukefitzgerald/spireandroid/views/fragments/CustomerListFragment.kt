package com.lukefitzgerald.spireandroid.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.Customer
import com.lukefitzgerald.spireandroid.views.models.CustomerListViewModel

private const val TAG = "CustomerListFragment"

class CustomerListFragment : Fragment() {

    private lateinit var customersRecyclerView: RecyclerView
    private var adapter: CustomerAdapter? = null

    private val customerListViewModel: CustomerListViewModel by lazy {
        ViewModelProviders.of(this).get(CustomerListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total Customers: ${customerListViewModel.customers.size}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_customer_list, container, false)

        customersRecyclerView = view.findViewById(R.id.customers_recycler_view) as RecyclerView
        customersRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI()

        return view
    }

    private fun updateUI() {
        val customers = customerListViewModel.customers
        adapter = CustomerAdapter(customers)
        customersRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): CustomerListFragment {
            return CustomerListFragment()
        }
    }

    private inner class CustomerHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var customer: Customer

        private val customerNameTextView: TextView = itemView.findViewById(R.id.customer_name)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(customer: Customer) {
            this.customer = customer
            customerNameTextView.text = "${this.customer.firstName} ${this.customer.lastName}"
        }

        override fun onClick(v: View) {
            Toast.makeText(context, "${customer.firstName} pressed!", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class CustomerAdapter(var customers: List<Customer>) : RecyclerView.Adapter<CustomerHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {

            val view = layoutInflater.inflate(R.layout.list_item_customer, parent, false)
            return CustomerHolder(view)
        }

        override fun getItemCount() = customers.size

        override fun onBindViewHolder(holder: CustomerHolder, position: Int) {

            val customer = customers[position]
            holder.bind(customer)
        }
    }
}