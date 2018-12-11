package com.bittya.domi.services.remote

import com.bittya.domi.models.moni.*
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthService {

    @POST("auth/login")
    fun login(@Body login: LoginRequest): Call<LoginResponse>

    @GET("auth/validate")
    fun validate(@Header("Authorization") authorization: String): Call<ValidateTokenResponse>

    @POST("auth/signup")
    fun signup(@Body credentials: SignUpRequest): Call<SignUpResponse>
}