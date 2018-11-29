package com.bittya.domi.models

import com.google.gson.annotations.SerializedName

class ValidateTokenResponse {

    @SerializedName("success")
    var success: Boolean? = null
    @SerializedName("message")
    var message: String? = null
    @SerializedName("data")
    var data: Data? = null

    class Data {
        @SerializedName("valid")
        var valid: Boolean? = null
    }

}