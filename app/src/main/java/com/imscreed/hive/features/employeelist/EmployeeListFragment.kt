package com.imscreed.hive.features.employeelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.employee_list_fragment.*

import com.imscreed.hive.R
import com.imscreed.hive.model.Employee
import com.imscreed.hive.utils.AppConstants
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class EmployeeListFragment : Fragment(), OnEmployeeClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = EmployeeListFragment()
    }

    private val TAG: String = EmployeeListFragment.javaClass.simpleName
    private lateinit var employeeListViewModel: EmployeeListViewModel
    private lateinit var employeeListAdapter: EmployeeListAdapter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeeListViewModel = ViewModelProvider(this, viewModelFactory).get(EmployeeListViewModel::class.java)
        employeeListViewModel.employees.observe(
            viewLifecycleOwner,
            Observer<MutableList<Employee>> { employees ->
                if (employees != null && employees.isNotEmpty()) {
                    emptyListStateView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    employeeListAdapter = EmployeeListAdapter(employees, this)
                    employeeRecyclerView.adapter = employeeListAdapter
                } else {
                    emptyListStateView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
        )
        return inflater.inflate(R.layout.employee_list_fragment, container, false)
    }

    override fun onItemClicked(employee: Employee) {
        val bundle = bundleOf(AppConstants.EMPLOYEE_KEY to employee)
        view?.findNavController()
            ?.navigate(R.id.action_employeeListFragment_to_employeeDetailsFragment, bundle)
    }

}
