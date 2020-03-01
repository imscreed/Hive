package com.imscreed.hive.model

import com.google.gson.annotations.SerializedName

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
data class Employee(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("email_address")
    val emailAddress: String,
    @SerializedName("biography")
    val biography: String,
    @SerializedName("photo_url_small")
    val photoUrlSmall: String,
    @SerializedName("photo_url_large")
    val photoUrlLarge: String,
    @SerializedName("team")
    val team: String,
    @SerializedName("employee_type")
    val employeeType: EmployeeType
)

data class EmployeeResponse(
    val employees: List<Employee>
)

enum class EmployeeType {
    FULL_TIME, PART_TIME, CONTRACTOR
}