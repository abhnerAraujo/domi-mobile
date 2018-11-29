package com.bittya.domi.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginResponse {

    @SerializedName("success")
    var success: Boolean? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("token")
    var token: String? = null

    companion object {
        const val TAG_TOKEN = "TOKEN"
    }

}