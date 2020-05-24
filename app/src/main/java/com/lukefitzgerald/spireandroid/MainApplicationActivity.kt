package com.lukefitzgerald.spireandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lukefitzgerald.spireandroid.views.fragments.CustomerListFragment

class MainApplicationActivity : AppCompatActivity() {

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
}
