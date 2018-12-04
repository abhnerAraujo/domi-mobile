package com.bittya.domi

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bittya.domi.fragments.RegistryInformationFragment
import com.bittya.domi.fragments.UserCredentialsFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity()
        , RegistryInformationFragment.OnFragmentInteractionListener
        , UserCredentialsFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container_registro.id
                , RegistryInformationFragment.newInstance()
                , "")
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        transaction.commit()
    }

    override fun onContinuarClicked() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(container_registro.id
                , UserCredentialsFragment.newInstance("", "")
                , "")
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        transaction.commit()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(container_registro.id
                , RegistryInformationFragment.newInstance()
                , "")
        transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        transaction.commit()

    }
}
