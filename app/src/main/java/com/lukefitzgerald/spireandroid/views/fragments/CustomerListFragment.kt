package com.lukefitzgerald.spireandroid.views.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.Customer
import com.lukefitzgerald.spireandroid.views.models.CustomerListViewModel
import java.util.*

private const val TAG = "CustomerListFragment"

class CustomerListFragment : Fragment() {

    //Required interface for hosting activities
    interface Callbacks {
        fun onCustomerSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var customersRecyclerView: RecyclerView
//    private var adapter: CustomerAdapter? = null
    private var adapter: CustomerAdapter? = CustomerAdapter(emptyList())

    private val customerListViewModel: CustomerListViewModel by lazy {
        ViewModelProviders.of(this).get(CustomerListViewModel::class.java)
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
        val view = inflater.inflate(R.layout.fragment_customer_list, container, false)

        customersRecyclerView = view.findViewById(R.id.customers_recycler_view) as RecyclerView
        customersRecyclerView.layoutManager = LinearLayoutManager(context)

//        updateUI()

        customersRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerListViewModel.customerListLiveData.observe(
            viewLifecycleOwner,
            Observer { customers ->
                customers?.let {
                    Log.i(TAG, "Got customers ${customers.size}")
                    updateUI(customers)
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
        inflater.inflate(R.menu.fragment_customer_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_customer -> {
                val customer = Customer()
                customer.firstName = "Luke"
                customer.lastName = "Fitzgerald"
                customerListViewModel.addCustomer(customer)
                callbacks?.onCustomerSelected(customer.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateUI(customers: List<Customer>) {
//        val customers = customerListViewModel.customers
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
//            Toast.makeText(context, "${customer.firstName} pressed!", Toast.LENGTH_SHORT).show()
            callbacks?.onCustomerSelected(customer.id)
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