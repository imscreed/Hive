package com.imscreed.hive.features.employeedetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.imscreed.hive.R
import javax.inject.Inject

class EmployeeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeDetailsFragment()
    }

    private lateinit var viewModel: EmployeeDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
