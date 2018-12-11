package com.bittya.domi.models.moni

import com.google.gson.annotations.SerializedName

class SignUpRequest(@SerializedName("email") var email: String
                    , @SerializedName("senha") var senha: String)