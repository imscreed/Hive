package com.imscreed.hive

import com.imscreed.hive.model.Employee
import com.imscreed.hive.model.EmployeeType

/*
*  "employees" : [
    {
      "uuid" : "some-uuid",
      "full_name" : "Eric Rogers",
      "phone_number" : "5556669870",
      "email_address" : "erogers.demo@squareup.com",
      "biography" : "A short biography describing the employee.",

      "photo_url_small” : "https://some.url/path1.jpg",
      "photo_url_large” : "https://some.url/path2.jpg",

      "team" : “Seller",
      "employee_type" : "FULL_TIME",
    },

    ...

  ]*/

class MockTestUtil {
    companion object {
        fun mockEmployee() =
            Employee(
                "some-uuid",
                "Eric Rogers",
                "5556669870",
                "erogers.demo@squareup.com",
                "A short biography describing the employee.",
                "https://some.url/path1.jpg",
                "https://some.url/path2.jpg",
                "Seller",
                EmployeeType.FULL_TIME
            )

        fun mockEmployeeList() =
            mutableListOf<Employee>(mockEmployee())
    }
}