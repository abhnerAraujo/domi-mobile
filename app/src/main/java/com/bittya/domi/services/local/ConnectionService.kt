package com.bittya.domi.services.local

import android.content.Context
import android.net.ConnectivityManager



class ConnectionService{

    companion object {

        fun test(context: Context): Boolean{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return (netInfo != null && netInfo.isConnectedOrConnecting)
        }
    }
}