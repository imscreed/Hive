package com.imscreed.hive.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkUtil(private val application: Application) {

    companion object{
        var isConnectedToNetwork: Boolean = false
    }
//    fun isNetworkAvailable(): Boolean {
//        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetworkInfo = connectivityManager.activeNetworkInfo
//        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
//    }
}