package com.lukefitzgerald.spireandroid.views.models

import androidx.lifecycle.ViewModel
import com.lukefitzgerald.spireandroid.models.Customer
import com.lukefitzgerald.spireandroid.repositories.CustomerRepository

class CustomerListViewModel : ViewModel() {

//    val customers = mutableListOf<Customer>()
//
//    init {
//        for (i in 0 until 20) {
//            val customer = Customer()
//            customer.firstName = "First$i"
//            customer.lastName = "Last$i"
//            customers += customer
//        }
//    }

    private val customerRepository = CustomerRepository.get()
    val customerListLiveData = customerRepository.getCustomers()
}