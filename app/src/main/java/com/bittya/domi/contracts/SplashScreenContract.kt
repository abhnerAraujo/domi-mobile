package com.bittya.domi.contracts

interface SplashScreenContract {

    interface presenter{
        fun onDestroy()
        fun requestDataFromServer(authorization: String)
    }

    interface MainView{
        fun showProgress()
        fun hideProgress()
        fun sendToLoginActivity(message: String?)
        fun sendToMainActivity()
        fun onResponseFailure(throwable: Throwable)
    }

}