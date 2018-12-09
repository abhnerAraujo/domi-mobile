package com.bittya.domi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bittya.domi.contracts.RegistryScreenContract
import com.bittya.domi.fragments.*
import com.bittya.domi.services.local.PreferencesService
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity()
        , RegistryBeforeStartFragment.OnFragmentInteractionListener
        , RegistryCredentialsFragment.OnFragmentInteractionListener
        , RegistryPersonalInformationFragment.OnFragmentInteractionListener
        , RegistryProfessionalInformationFragment.OnFragmentInteractionListener
        , RegistryFinishedFragment.OnFragmentInteractionListener
        , RegistryScreenContract.RegistryView{

    private val beforeStartFragment = RegistryBeforeStartFragment.newInstance()
    private val credentialsFragment = RegistryCredentialsFragment.newInstance()
    private val finishedFragment = RegistryFinishedFragment.newInstance()
    private val personalInformationFragment = RegistryPersonalInformationFragment.newInstance()
    private val professionalInformationFragment = RegistryProfessionalInformationFragment.newInstance()

    override fun onContinuarToCredentialsClicked() {
        showFragment(credentialsFragment ,RegistryCredentialsFragment.TAG)
    }

    override fun onVoltarToInformationClicked() {
        showFragment(beforeStartFragment, RegistryBeforeStartFragment.TAG)
    }

    override fun onContinuarToPersonalInfoClicked() {
        showFragment(personalInformationFragment, RegistryPersonalInformationFragment.TAG)
    }

    override fun onContinuarToProfessionalInfoClicked() {
        showFragment(professionalInformationFragment, RegistryProfessionalInformationFragment.TAG)
    }

    override fun onCancelarToLoginActivity() {
        finish()
    }

    override fun onContinuarToRegistryDoneClicked() {
        showFragment(finishedFragment, RegistryFinishedFragment.TAG)
    }

    override fun onVoltarToPersonalInfoClicked() {
        showFragment(personalInformationFragment, RegistryPersonalInformationFragment.TAG)
    }

    override fun onFinalizarToMainActivityClicked() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(resources.getString(R.string.tag_domi_activities), "Abrindo activity: ${this.javaClass.simpleName}")
        setContentView(R.layout.activity_registration)

        showFragment(beforeStartFragment ,RegistryBeforeStartFragment.TAG)

    }

    private fun showFragment(fragment: Fragment, tag: String){
        supportFragmentManager.apply {
            if(this.findFragmentByTag(tag) == null){
                this.beginTransaction().let {
                    Log.d(getString(R.string.tag_domi_fragments), "adicionando $tag")
                    it.setCustomAnimations(R.anim.grow_fade_in_from_bottom, R.anim.fade_out)
                    it.add(container_registro.id ,fragment, tag)
                }.commit()
            }else{
                this.beginTransaction().let {
                    Log.d(getString(R.string.tag_domi_fragments), "trocando por $tag")
                    it.setCustomAnimations(R.anim.grow_fade_in_from_bottom, R.anim.fade_out)
                    it.replace(container_registro.id, fragment, tag)
                }.commit()
            }
        }
    }

    override fun showProgress() {
        progress_bar_registry.visibility = View.VISIBLE
    }


    override fun hideProgress() {
        progress_bar_registry.visibility = View.GONE
    }

    override fun sendToMainActivity(token: String) {
        //Salva o token antes de entrar
        PreferencesService(this)
                .saveStringPreference(resources.getString(R.string.token), token)
        startActivity(Intent(this, MainActivity::class.java)
                .putExtra(resources.getString(R.string.tag_mensagem), getString(R.string.info_bem_vindo_a)))
        finish()
    }

    override fun makeToast(texto: String){
        Toast.makeText(this, texto, Toast.LENGTH_LONG)
                .show()
    }

    override fun makeSnackBar(texto: String) {
        Snackbar.make(container_registro, texto, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun onResponseFailure(throwable: Throwable) {
        makeSnackBar(throwable.message.toString())
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(getString(R.string.tag_domi_activities), "Finalizando activity: ${this.javaClass.simpleName}")
    }
}
