package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter

import com.bittya.domi.R
import kotlinx.android.synthetic.main.fragment_user_personal_information.*

class RegistryPersonalInformationFragment : androidx.fragment.app.Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var arrayAdapter: ArrayAdapter<CharSequence>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_personal_information, container, false)
    }

    private fun onContinuarPressed() {
        listener?.onContinuarToProfessionalInfoClicked()
    }

    private fun onCancelarPressed() {
        listener?.onCancelarToLoginActivity()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            arrayAdapter = ArrayAdapter.createFromResource(context, R.array.sexos, android.R.layout.simple_spinner_item)
            listener = context
        } else {
            throw Throwable(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_voltar_to_login.setOnClickListener {
            onCancelarPressed()
        }
        btn_continuar_to_professional.setOnClickListener {
            onContinuarPressed()
        }
        arrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spn_reg_pessoal_sexo.adapter = arrayAdapter
        val fadeInMoveBack = AnimationUtils.loadAnimation(context, R.anim.fade_in_move_back)
        fadeInMoveBack.reset()
        tvw_passo_dois.startAnimation(fadeInMoveBack)
        tvw_podemos_nos_apresentar.startAnimation(fadeInMoveBack)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onContinuarToProfessionalInfoClicked()
        fun onCancelarToLoginActivity()
    }

    companion object {

        const val TAG = "RegistryPersonalInformationFragment"

        @JvmStatic
        fun newInstance() =
                RegistryPersonalInformationFragment()
    }
}
