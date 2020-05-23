package com.lukefitzgerald.spireandroid.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.lukefitzgerald.spireandroid.models.Customer
import java.util.*

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer")
    fun getCustomers(): List<Customer>

    @Query("SELECT * FROM customer WHERE id=(:id)")
    fun getCustomer(id: UUID): Customer?
}