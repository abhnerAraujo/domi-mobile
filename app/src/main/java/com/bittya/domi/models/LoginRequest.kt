package com.bittya.domi.models

import com.google.gson.annotations.SerializedName

class LoginRequest(@SerializedName("email") var email: String
                   , @SerializedName("senha") var senha: String) {

    companion object {
        const val TAG_EMAIL = "EMAIL"
    }
}