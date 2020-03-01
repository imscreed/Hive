package com.imscreed.hive.features.employeelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.imscreed.hive.R
import com.imscreed.hive.model.Employee
import kotlinx.android.synthetic.main.employee_list_row_item.view.*

class EmployeeListAdapter(private val employees : List<Employee>): RecyclerView.Adapter<EmployeeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.employee_list_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.name.text = employees.get(position).fullName
        holder.email.text = employees.get(position).emailAddress
        holder.team.text = employees.get(position).team
        holder.photo.load(employees.get(position).photoUrlSmall) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
            transformations(CircleCropTransformation())
        }
    }
}

class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var name = itemView.nameView
    var email = itemView.emailView
    var team = itemView.teamView
    var photo = itemView.imageView
}
