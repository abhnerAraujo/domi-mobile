package com.bittya.domi.services.intractors

import android.util.Log
import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.models.moni.*
import com.bittya.domi.services.remote.AuthService
import com.bittya.domi.services.remote.RetroClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthIntractor : AuthContract.GetAuthIntractor{

    override fun validateToken(token: String, onFinishedValidateListener: AuthContract.GetAuthIntractor.OnFinishedValidateListener) {
        try {
            val service = RetroClient.createService(AuthService::class.java)
            val call = service.validate(token)
            Log.d("domi_request", call.request().url().toString())
            call.enqueue(object: Callback<ValidateTokenResponse> {
                override fun onFailure(call: Call<ValidateTokenResponse>?, t: Throwable?) {
                    onFinishedValidateListener.onValidateFailure(t ?: Throwable("Erro ao tentar validar acesso"))
                }
                override fun onResponse(call: Call<ValidateTokenResponse>?, response: Response<ValidateTokenResponse>?) {
                    val arrayList = ArrayList<ValidateTokenResponse>()
                    response?.body()?.apply{
                        if(this.success == true){
                            Log.d("domi_response", this.message)
                        }
                        arrayList.add(this)
                    }
                    onFinishedValidateListener.onValidateFinished(arrayList)
                }
            })
        }catch (t: Throwable){
            onFinishedValidateListener.onValidateFailure(Throwable("Erro ao tentar validar acesso"))
        }
    }

    override fun doLogin(body: LoginRequest, onFinishedLoginListener: AuthContract.GetAuthIntractor.OnFinishedLoginListener) {
        try {
            val service = RetroClient.createService(AuthService::class.java)
            val call = service.login(body)
            call.enqueue(object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    throw  Throwable(t ?: Throwable("Erro ao tentar validar acesso"))
                }
                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                    val arrayList = ArrayList<LoginResponse>()
                    if(response?.isSuccessful == true){
                        response.body()?.apply {
                            Log.d("domi_request", this.message)
                            arrayList.add(this)
                        }
                        onFinishedLoginListener.onLoginFinished(arrayList)
                    }else{
                        throw Throwable("Erro inesperado")
                    }
                }
            })
        } catch (t: Throwable){
            onFinishedLoginListener.onLoginFailure(t)
        }
    }

    override fun signUp(body: SignUpRequest, onFinishedSignUpListener: AuthContract.GetAuthIntractor.OnFinishedSignUpListener) {
        try {
            Log.d("domi_teste", "Signup")
            val service = RetroClient.createService(AuthService::class.java)
            val call = service.signup(body)
            call.enqueue(object: Callback<SignUpResponse> {
                override fun onFailure(call: Call<SignUpResponse>?, t: Throwable?) {
                    throw  Throwable(t ?: Throwable("Erro no cadastro do usuário"))
                }
                override fun onResponse(call: Call<SignUpResponse>?, response: Response<SignUpResponse>?) {
                    val arrayList = ArrayList<SignUpResponse>()
                    if(response?.isSuccessful == true){
                        response.body()?.apply {
                            Log.d("domi_request", this.message)
                            arrayList.add(this)
                        }
                        onFinishedSignUpListener.onSignUpFinished(arrayList)
                    }else{
                        throw Throwable("Erro inesperado")
                    }
                }
            })
        } catch (t: Throwable){
            onFinishedSignUpListener.onSignUpFailure(t)
        }
    }
}