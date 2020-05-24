package com.lukefitzgerald.spireandroid.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lukefitzgerald.spireandroid.models.Customer
import java.util.*

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
//    fun getCustomers(): List<Customer>
    fun getCustomers(): LiveData<List<Customer>>

    @Query("SELECT * FROM customer WHERE id=(:id)")
//    fun getCustomer(id: UUID): Customer?
    fun getCustomer(id: UUID): LiveData<Customer?>

    @Update
    fun updateCustomer(customer: Customer)

    @Insert
    fun addCustomer(customer: Customer)
}