package com.bittya.domi.models

import com.google.gson.annotations.SerializedName

class LoginRequest(@SerializedName("user") var user: User) {
    class User(@SerializedName("email") var email: String
               , @SerializedName("password") var senha: String)
}