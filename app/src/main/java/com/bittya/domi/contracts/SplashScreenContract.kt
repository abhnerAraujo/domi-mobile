package com.bittya.domi.contracts

interface SplashScreenContract {

    interface presenter{
        fun onDestroy()
        fun requestDataFromServer(authorization: String)
    }

    interface MainView{
        fun sendToLoginActivity(message: String?)
        fun sendToMainActivity()
        fun onResponseFailure(throwable: Throwable)
    }

}