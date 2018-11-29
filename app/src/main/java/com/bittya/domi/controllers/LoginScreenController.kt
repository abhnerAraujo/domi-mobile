package com.bittya.domi.controllers

import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.contracts.LoginScreenContract
import com.bittya.domi.models.LoginRequest
import com.bittya.domi.models.LoginResponse

class LoginScreenController(private var login_screen: LoginScreenContract.LoginView, private var authIntractor: AuthContract.GetAuthIntractor)
    : AuthContract.GetAuthIntractor.OnFinishedLoginListener
        , LoginScreenContract.presenter {

    override fun onLoginFinished(loginResponse: ArrayList<LoginResponse>) {
        if(loginResponse.size > 0){
            if(loginResponse[0].success == true){
                login_screen.sendToMainActivity(loginResponse[0].token ?: "")
            } else {
                onLoginFailure(Throwable(loginResponse[0].message))
            }
        }
    }

    override fun onLoginFailure(t: Throwable) {
        login_screen.makeSnackBar(t.message ?: "Erro inesperado")
    }

    override fun doLogin(body: LoginRequest) {
        authIntractor.doLogin(body, this)
    }
}