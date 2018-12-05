package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bittya.domi.R
import kotlinx.android.synthetic.main.fragment_user_credentials.*

class RegistryCredentialsFragment : androidx.fragment.app.Fragment() {
    private var listener: OnFragmentInteractionListener? = null

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
        btn_voltar_to_inicio.setOnClickListener {
            onVoltarPressed()
        }

    }

    private fun onContinuarPressed() {
        listener?.onContinuarToPersonalInfoClicked()
    }

    private fun onVoltarPressed() {
        listener?.onVoltarToInformationClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onContinuarToPersonalInfoClicked()
        fun onVoltarToInformationClicked()
    }

    companion object {

        const val TAG = "RegistryCredentialsFragment"

        @JvmStatic
        fun newInstance() =
                RegistryCredentialsFragment()
    }
}
