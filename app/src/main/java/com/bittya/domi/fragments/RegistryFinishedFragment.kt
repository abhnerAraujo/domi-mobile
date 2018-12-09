package com.bittya.domi.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.bittya.domi.R
import kotlinx.android.synthetic.main.fragment_registry_finished.*


class RegistryFinishedFragment : androidx.fragment.app.Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registry_finished, container, false)
    }

    fun onFinalizarPressed() {
        listener?.onFinalizarToMainActivityClicked()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw Throwable(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_comecar_to_main.setOnClickListener { onFinalizarPressed() }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFinalizarToMainActivityClicked()
    }

    companion object {

        const val TAG = "RegistryFinishedFragment"

        @JvmStatic
        fun newInstance() =
                RegistryFinishedFragment()
    }
}
