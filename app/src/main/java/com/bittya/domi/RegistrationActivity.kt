package com.bittya.domi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bittya.domi.fragments.RegistryBeforeStartFragment
import com.bittya.domi.fragments.RegistryCredentialsFragment
import com.bittya.domi.fragments.RegistryPersonalInformationFragment
import com.bittya.domi.fragments.RegistryProfessionalInformationFragment
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity()
        , RegistryBeforeStartFragment.OnFragmentInteractionListener
        , RegistryCredentialsFragment.OnFragmentInteractionListener
        , RegistryPersonalInformationFragment.OnFragmentInteractionListener
        , RegistryProfessionalInformationFragment.OnFragmentInteractionListener{

    override fun onContinuarToCredentialsClicked() {
        showFragment(RegistryCredentialsFragment.TAG)
    }

    override fun onVoltarToInformationClicked() {
        showFragment(RegistryBeforeStartFragment.TAG)
    }

    override fun onContinuarToPersonalInfoClicked() {
        showFragment(RegistryPersonalInformationFragment.TAG)
    }

    override fun onContinuarToProfessionalInfoClicked() {
        showFragment(RegistryProfessionalInformationFragment.TAG)
    }

    override fun onCancelarToLoginActivity() {
        finish()
    }

    override fun onContinuarToRegistryDoneClicked() {
    }

    override fun onVoltarToPersonalInfoClicked() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        showFragment(RegistryBeforeStartFragment.TAG)

    }

    private fun showFragment(tag: String){
        val currentFragment: Fragment? = supportFragmentManager.fragments.lastOrNull()
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        val fragmentExists = fragment != null
        if (fragment == null) {
            fragment = when (tag) {
                RegistryProfessionalInformationFragment.TAG -> RegistryProfessionalInformationFragment.newInstance()
                RegistryPersonalInformationFragment.TAG -> RegistryPersonalInformationFragment.newInstance()
                RegistryCredentialsFragment.TAG -> RegistryCredentialsFragment.newInstance()
                else -> RegistryBeforeStartFragment.newInstance()
            }
        }

        val transaction = supportFragmentManager.beginTransaction()

        if (currentFragment != null) {
            Log.i("domi_fragments", "hiding " + currentFragment.javaClass.simpleName)
            transaction.hide(currentFragment)
        }

        if (fragmentExists) {
            Log.i("domi_fragments", "showing " + fragment.javaClass.simpleName)
            transaction.show(fragment)
        } else {
            Log.i("domi_fragments", "adding " + fragment.javaClass.simpleName)
            transaction.add(container_registro.id, fragment, tag)
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        }

        transaction.commit()
    }
}
