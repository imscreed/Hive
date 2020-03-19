package com.imscreed.hive.features.employeelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imscreed.hive.model.Employee
import com.imscreed.hive.repository.EmployeeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListViewModel @Inject constructor(private var repository: EmployeeRepository) : ViewModel() {

    init {
        fetchEmployeesFromRepository()
    }

    val _employeesLiveData = MutableLiveData<MutableList<Employee>>()
    val employees: LiveData<MutableList<Employee>>
        get() = _employeesLiveData

    private fun fetchEmployeesFromRepository() {
        viewModelScope.launch {
            val employeeList = repository.fetchEmployeesFromRemote()
            _employeesLiveData.postValue(employeeList)
        }
    }
}
