package com.bittya.domi.models.moni

import com.google.gson.annotations.SerializedName

class LoginResponse {
    @SerializedName("success")
    var success: Boolean? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("data")
    var data: Data? = null
    class Data {
        @SerializedName("token")
        var token: String? = null
        @SerializedName("user")
        var user: User? = null
        class User {
            @SerializedName("_id")
            var id: String? = null
            @SerializedName("message")
            var email: String? = null
        }
    }
}