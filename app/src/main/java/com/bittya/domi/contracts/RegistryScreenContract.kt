package com.bittya.domi.contracts

import com.bittya.domi.models.LoginRequest

interface RegistryScreenContract {

    interface Presenter{
        fun signUp(body: LoginRequest)
    }

    interface RegistryView{
        fun showProgress()
        fun hideProgress()
        fun sendToMainActivity(token: String)
        fun makeSnackBar(texto: String)
        fun makeToast(texto: String)
        fun onResponseFailure(throwable: Throwable)
    }

}