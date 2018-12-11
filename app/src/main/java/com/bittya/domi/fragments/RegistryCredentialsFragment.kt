package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import com.bittya.domi.R
import com.bittya.domi.models.data.RegistroUsuario
import com.bittya.domi.utils.Validacoes
import kotlinx.android.synthetic.main.fragment_user_credentials.*

class RegistryCredentialsFragment :
        androidx.fragment.app.Fragment()
        , TextWatcher{
    private var listener: OnFragmentInteractionListener? = null
    private val usuario = RegistroUsuario.Usuario("", "")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_credentials, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_continuar_to_personal.setOnClickListener {
            onContinuarPressed()
        }
        btn_voltar_to_login.setOnClickListener {
            onVoltarPressed()
        }
        edt_email.addTextChangedListener(this)
        edt_repetir_email.addTextChangedListener(this)
        edt_senha.addTextChangedListener(this)
        edt_repetir_senha.addTextChangedListener(this)
        val fadeInMoveBack = AnimationUtils.loadAnimation(context, R.anim.fade_in_move_back)
        fadeInMoveBack.reset()
        tvw_passo_um.startAnimation(fadeInMoveBack)
        tvw_get_credential.startAnimation(fadeInMoveBack)
        btn_continuar_to_personal.isEnabled = false
    }

    private fun onContinuarPressed() {
        usuario.apply {
            this.email = edt_email.text.toString()
            this.senha = edt_senha.text.toString()
        }?.also { listener?.onContinuarToPersonalInfoClicked(it) }
    }

    private fun onVoltarPressed() {
        usuario.apply {
            this.email = edt_email.text.toString()
            this.senha = edt_senha.text.toString()
        }.also { listener?.onVoltarToInformationClicked() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if(hidden){
            edt_repetir_email.setText("")
            edt_repetir_senha.setText("")
            edt_senha.setText("")
            edt_email.setText("")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onContinuarToPersonalInfoClicked(usuario: RegistroUsuario.Usuario)
        fun onVoltarToInformationClicked()
    }

    override fun afterTextChanged(p0: Editable?) {
        if (edt_email.isFocused) {
            if(!Validacoes.isEmailValid(p0.toString().trim())){
                edt_email.error = context?.resources?.getString(R.string.error_email_invalido)
            } else {
                edt_email.error = null
            }
        } else if(edt_repetir_email.isFocused){
            if(edt_repetir_email.text?.toString()?.trim() == edt_email.text?.toString()?.trim()){
                edt_repetir_email.error = null
            }else{
                edt_repetir_email.error = getString(R.string.error_email_diferentes)
            }
        } else if(edt_senha.isFocused){
            if(!Validacoes.isPasswordValid(p0.toString())){
                edt_senha.error = getString(R.string.error_senha_padrao_errado)
            } else {
                edt_senha.error = null
            }
        } else if(edt_repetir_senha.isFocused) {
            if(edt_repetir_senha.text?.toString()?.trim() == edt_senha.text?.toString()?.trim()){
                edt_repetir_senha.error = null
            }else{
                edt_repetir_senha.error = getString(R.string.error_senhas_diferentes)
            }
        }
        habilitarOuDesabilitarContinuar()
    }

    private fun habilitarOuDesabilitarContinuar(){
        btn_continuar_to_personal.isEnabled = (edt_repetir_senha.error == null
            && edt_senha.error == null
            && edt_repetir_email.error == null
            && edt_email.error == null)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    companion object {

        const val TAG = "RegistryCredentialsFragment"

        @JvmStatic
        fun newInstance() =
                RegistryCredentialsFragment()
    }
}
