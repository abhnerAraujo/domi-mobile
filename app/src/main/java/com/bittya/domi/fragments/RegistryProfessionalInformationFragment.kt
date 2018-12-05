package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bittya.domi.R

class RegistryProfessionalInformationFragment : androidx.fragment.app.Fragment() {
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_professional_information, container, false)
    }

    fun onFinishPressed() {
        listener?.onContinuarToRegistryDoneClicked()
    }

    fun onVoltarPressed() {
        listener?.onContinuarToRegistryDoneClicked()
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    interface OnFragmentInteractionListener {
        fun onContinuarToRegistryDoneClicked()
        fun onVoltarToPersonalInfoClicked()
    }

    companion object {

        const val TAG = "RegistryProfessionalInformationFragment"

        @JvmStatic
        fun newInstance() =
                RegistryProfessionalInformationFragment()
    }
}
