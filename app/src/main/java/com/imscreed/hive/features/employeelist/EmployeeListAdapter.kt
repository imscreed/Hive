package com.imscreed.hive.features.employeelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.imscreed.hive.R
import com.imscreed.hive.model.Employee
import kotlinx.android.synthetic.main.employee_list_row_item.view.*

class EmployeeListAdapter(
    private val employees: List<Employee>,
    private val employeeClickListener: OnEmployeeClickListener
) : RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.employee_list_row_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee, employeeClickListener)
    }
}

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var name: TextView = itemView.nameView
    private var email: TextView = itemView.emailView
    private var team: TextView = itemView.teamView
    private var photo: ImageView = itemView.imageView

    fun bind(employee: Employee, employeeClickListener: OnEmployeeClickListener) {
        name.text = employee.fullName
        email.text = employee.emailAddress
        team.text = employee.team

        photo.load(employee.photoUrlSmall) {
            crossfade(true)
            placeholder(R.drawable.placeholder)
            transformations(CircleCropTransformation())
        }

        itemView.setOnClickListener {
            employeeClickListener.onItemClicked(employee)
        }
    }
}

interface OnEmployeeClickListener {
    fun onItemClicked(employee: Employee)
}
