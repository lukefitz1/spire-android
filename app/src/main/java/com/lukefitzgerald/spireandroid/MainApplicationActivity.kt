package com.lukefitzgerald.spireandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lukefitzgerald.spireandroid.models.Artist
import com.lukefitzgerald.spireandroid.views.fragments.ArtistListFragment
import com.lukefitzgerald.spireandroid.views.fragments.CustomerListFragment
import com.lukefitzgerald.spireandroid.views.fragments.GeneralInformationListFragment
import java.util.*

class MainApplicationActivity : AppCompatActivity(), CustomerListFragment.Callbacks, GeneralInformationListFragment.Callbacks, ArtistListFragment.Callbacks {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_application)
        bottomNavigation = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

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
//        Log.d(TAG, "MainApplicationActivity.onCustomerSelected: $customerId")
        val fragment = CustomerListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onArtistSelected(artistId: UUID) {
//        Log.d(TAG, "MainApplicationActivity.onArtistSelected: $artistId")
        val fragment = ArtistListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onGeneralInformationSelected(generalInformationId: UUID) {
//        Log.d(TAG, "MainApplicationActivity.onGeneralInformationSelected: $generalInformationId")
        val fragment = GeneralInformationListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_customers -> {
                val fragment = CustomerListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
                true
            }
            R.id.navigation_artists -> {
                val fragment = ArtistListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
                true
            }
            R.id.navigation_general_information -> {
                val fragment = GeneralInformationListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
                true
            }
            else -> false
        }
    }
}
