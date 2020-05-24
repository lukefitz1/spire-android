package com.lukefitzgerald.spireandroid.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.lukefitzgerald.spireandroid.R
import com.lukefitzgerald.spireandroid.models.Customer

class CustomerFragment : Fragment() {

    private lateinit var customer: Customer
    private lateinit var newCustomerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        customer = Customer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_customer, container, false)

        newCustomerButton = view.findViewById(R.id.new_customer_button) as Button

        return view
    }

    override fun onStart() {
        super.onStart()

//        newCustomerButton.setOnClickListener {
//            Intent(Intent.ACTION_SEND).apply {
//                type = "text/plain"
//                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
//                putExtra(
//                    Intent.EXTRA_SUBJECT,
//                    getString(R.string.crime_report_subject)
//                )
//            }.also { intent ->
//                //                startActivity(intent) <- This will use a default app if it is selected
//                // the following code will always show the chooser, even if the user has a default app already chosen for the intent
//                val chooserIntent = Intent.createChooser(intent, getString(R.string.send_report))
//                startActivity(chooserIntent)
//            }
//        }
    }
}