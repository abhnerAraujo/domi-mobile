package com.bittya.domi.controllers

import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.contracts.SplashScreenContract
import com.bittya.domi.models.ValidateTokenResponse

class SplashScreenController(private var splashScreenView: SplashScreenContract.MainView?, private var authIntractor: AuthContract.GetAuthIntractor)
    : AuthContract.GetAuthIntractor.OnFinishedValidateListener, SplashScreenContract.presenter{

    companion object {
        const val TAG_TOKEN = "TOKEN"
    }

    override fun onDestroy() {
        this.splashScreenView = null
    }

    override fun requestDataFromServer(authorization: String) {
        authIntractor.validateToken(authorization,this)
    }

    override fun onValidateFinished(loginResponse: ArrayList<ValidateTokenResponse>) {
        if(loginResponse.size != 0){
            if (loginResponse[0].success == true) {
                splashScreenView?.sendToMainActivity()
            } else {
                splashScreenView?.sendToLoginActivity("Sua sessão expirou")
            }
        }
    }

    override fun onValidateFailure(t: Throwable) {
        splashScreenView?.sendToLoginActivity(t.message ?: "Erro inesperado")
    }


}