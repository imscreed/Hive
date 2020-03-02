package com.imscreed.hive.features.employeedetails

import android.app.Notification
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import coil.transform.CircleCropTransformation
import com.imscreed.hive.R
import com.imscreed.hive.model.Employee
import com.imscreed.hive.utils.AppConstants
import kotlinx.android.synthetic.main.employee_details_fragment.*


class EmployeeDetailsFragment : Fragment(), View.OnClickListener {

    private val TAG = EmployeeDetailsFragment.javaClass.simpleName
    private var mEmployee: Employee? = null

    companion object {
        fun newInstance() = EmployeeDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mEmployee = arguments?.getParcelable(AppConstants.EMPLOYEE_KEY)
        return inflater.inflate(R.layout.employee_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (mEmployee != null) {
            profilePicView.load(mEmployee!!.photoUrlSmall) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                transformations(CircleCropTransformation())
            }
            nameView.text = mEmployee!!.fullName
            teamView.text = mEmployee!!.team
            biographyView.text = mEmployee!!.biography
            typeView.text = mEmployee!!.employeeType.toString()
            emailButton.setOnClickListener(this)
            phoneButton.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.emailButton -> startActivity(
                    Intent(
                        Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + mEmployee?.emailAddress)
                    )
                )
                R.id.phoneButton -> startActivity(
                    Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mEmployee?.phoneNumber)
                    )
                )
            }
        }
    }
}
