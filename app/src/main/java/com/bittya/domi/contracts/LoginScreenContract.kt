package com.bittya.domi.contracts

import com.bittya.domi.models.LoginRequest

interface LoginScreenContract {

    interface Presenter{
        fun doLogin(body: LoginRequest)
    }

    interface LoginView{
        fun showProgress()
        fun hideProgress()
        fun sendToMainActivity(token: String)
        fun makeSnackBar(texto: String)
        fun onResponseFailure(throwable: Throwable)
    }

}