package com.imscreed.hive.features.employeelist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.employee_list_fragment.*

import com.imscreed.hive.R
import com.imscreed.hive.model.Employee

class EmployeeListFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    private val TAG: String = EmployeeListFragment.javaClass.simpleName
    private lateinit var employeeListViewModel: EmployeeListViewModel
    private lateinit var employeeListAdapter: EmployeeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeListViewModel = ViewModelProvider(this).get(EmployeeListViewModel::class.java)
        employeeListViewModel.employees.observe(
            viewLifecycleOwner,
            Observer<MutableList<Employee>> { employees ->
                Log.d(TAG, employees.toString())
                employeeListAdapter = EmployeeListAdapter(employees)
                employeeRecyclerView.adapter = employeeListAdapter

            }
        )
        return inflater.inflate(R.layout.employee_list_fragment, container, false)
    }

}
