package com.bittya.domi.controllers

import android.util.Log
import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.contracts.RegistryScreenContract
import com.bittya.domi.models.data.RegistroUsuario
import com.bittya.domi.models.moni.SignUpRequest
import com.bittya.domi.models.moni.SignUpResponse

class RegistryScreenController(private var registryScreenView: RegistryScreenContract.RegistryView, private val authIntractor: AuthContract.GetAuthIntractor)
    : RegistryScreenContract.Presenter, AuthContract.GetAuthIntractor.OnFinishedSignUpListener{

    override fun signUp(body: RegistroUsuario.Usuario) {
        registryScreenView.showProgress()
        authIntractor.signUp(SignUpRequest(body.email, body.senha), this)
    }

    override fun onSignUpFinished(signUpResponse: ArrayList<SignUpResponse>) {
        if(signUpResponse.size > 0) {
            Log.d("domi_request", "Cadastro bem sucedido com token: ${signUpResponse[0].data?.token}")
            registryScreenView.openPersonalInformationFragment(
                    RegistroUsuario.Usuario("", "").apply {
                this.email = signUpResponse[0].data?.user?.email ?: ""
                this.token = signUpResponse[0].data?.token ?: ""
            })
        } else {
            onSignUpFailure(Throwable("Erro inesperado. Tente fazer login."))
        }
    }

    override fun onSignUpFailure(t: Throwable) {
        registryScreenView.onResponseFailure(t)
    }
}