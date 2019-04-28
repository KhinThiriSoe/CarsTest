package com.sevenpeakssoftware.khinthirisoe.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun hasNetwork(context: Context): Boolean {
        var isConnected = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) isConnected = true
        return isConnected

    }

}
