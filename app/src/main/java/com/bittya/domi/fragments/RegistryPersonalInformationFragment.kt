package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bittya.domi.R

class RegistryPersonalInformationFragment : androidx.fragment.app.Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_personal_information, container, false)
    }

    fun onContinuarPressed() {
        listener?.onContinuarToProfessionalInfoClicked()
    }

    fun onCancelarPressed() {
        listener?.onCancelarToLoginActivity()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
