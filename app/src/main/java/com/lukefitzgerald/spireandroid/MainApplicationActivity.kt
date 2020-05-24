package com.lukefitzgerald.spireandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lukefitzgerald.spireandroid.views.fragments.CustomerListFragment
import java.util.*

private const val TAG = "TAG"

class MainApplicationActivity : AppCompatActivity(), CustomerListFragment.Callbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_application)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if (currentFragment == null) {
            val fragment = CustomerListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCustomerSelected(customerId: UUID) {
        Log.d(TAG, "MainApplicationActivity.onCustomerSelected: $customerId")
    }
}
