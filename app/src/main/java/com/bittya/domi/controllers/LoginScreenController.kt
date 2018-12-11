package com.bittya.domi.controllers

import android.util.Log
import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.contracts.LoginScreenContract
import com.bittya.domi.models.moni.LoginRequest
import com.bittya.domi.models.moni.LoginResponse

class LoginScreenController(private var login_screen: LoginScreenContract.LoginView, private var authIntractor: AuthContract.GetAuthIntractor)
    : AuthContract.GetAuthIntractor.OnFinishedLoginListener
        , LoginScreenContract.Presenter {

    override fun onLoginFinished(loginResponse: ArrayList<LoginResponse>) {
        if(loginResponse.size > 0){
            if(loginResponse[0].success == true){
                Log.d("domi_auth", "Login com token: ${loginResponse[0].data?.token}")
                login_screen.makeToast(loginResponse[0].message ?: "Você entrou")
                login_screen.sendToMainActivity(loginResponse[0].data?.token ?: "")
            } else {
                onLoginFailure(Throwable(loginResponse[0].message))
            }
        }
    }

    override fun onLoginFailure(t: Throwable) {
        login_screen.onResponseFailure(t)
    }

    override fun doLogin(body: LoginRequest) {
        authIntractor.doLogin(body, this)
    }
}