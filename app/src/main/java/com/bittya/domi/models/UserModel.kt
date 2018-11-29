package com.bittya.domi.models

class UserModel {

    var email = ""
    var password = ""

    override fun toString(): String {
        return "UserModel{" +
                "email='$email', " +
                "password='$password'}"
    }

}