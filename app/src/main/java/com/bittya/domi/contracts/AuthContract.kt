package com.bittya.domi.contracts

import com.bittya.domi.models.moni.*

interface AuthContract {
    interface GetAuthIntractor {

        interface OnFinishedValidateListener {
            fun onValidateFinished(loginResponse: ArrayList<ValidateTokenResponse>)
            fun onValidateFailure(t: Throwable)
        }

        fun validateToken(token: String, onFinishedValidateListener: OnFinishedValidateListener)

        interface OnFinishedLoginListener {
            fun onLoginFinished(loginResponse: ArrayList<LoginResponse>)
            fun onLoginFailure(t: Throwable)
        }

        fun doLogin(body: LoginRequest, onFinishedLoginListener: OnFinishedLoginListener)

        interface OnFinishedSignUpListener {
            fun onSignUpFinished(signUpResponse: ArrayList<SignUpResponse>)
            fun onSignUpFailure(t: Throwable)
        }

        fun signUp(body: SignUpRequest, onFinishedSignUpListener: OnFinishedSignUpListener)
    }




}