package com.bittya.domi.contracts

import com.bittya.domi.models.data.RegistroUsuario

interface RegistryScreenContract {

    interface Presenter{
        fun signUp(body: RegistroUsuario.Usuario)
    }

    interface RegistryView{
        fun showProgress()
        fun hideProgress()
        fun sendToMainActivity()
        fun openPersonalInformationFragment(usuario: RegistroUsuario.Usuario)
        fun makeSnackBar(texto: String)
        fun makeToast(texto: String)
        fun onResponseFailure(throwable: Throwable)
    }

}