package com.lukefitzgerald.spireandroid.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.lukefitzgerald.spireandroid.database.SpireDatabase
import com.lukefitzgerald.spireandroid.models.Customer
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "spire-database"

class CustomerRepository private constructor(context: Context) {

    private val database : SpireDatabase = Room.databaseBuilder(
        context.applicationContext,
        SpireDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val customerDao = database.customerDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getCustomers(): LiveData<List<Customer>> = customerDao.getCustomers()

    fun getCustomer(id: UUID): LiveData<Customer?> = customerDao.getCustomer(id)

    fun updateCustomer(customer: Customer) {
        executor.execute {
            customerDao.updateCustomer(customer)
        }
    }

    fun addCustomer(customer: Customer) {
        executor.execute {
            customerDao.addCustomer(customer)
        }
    }

    companion object {
        private var INSTANCE: CustomerRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CustomerRepository(context)
            }
        }

        fun get(): CustomerRepository {
            return INSTANCE ?: throw IllegalStateException("CustomerRepository must be initialized")
        }
    }
}