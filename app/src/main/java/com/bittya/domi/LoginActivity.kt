package com.bittya.domi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.bittya.domi.contracts.AuthContract
import com.bittya.domi.contracts.LoginScreenContract
import com.bittya.domi.controllers.LoginScreenController
import com.bittya.domi.models.LoginRequest
import com.bittya.domi.services.intractors.AuthIntractor
import com.bittya.domi.services.local.ConnectionService
import com.bittya.domi.services.local.PreferencesService
import com.bittya.domi.utils.Validacoes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginScreenContract.LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val message = intent.getStringExtra(TAG_MESSAGE)
        if(message != null){
            Toast.makeText(this, message, Toast.LENGTH_LONG)
                    .show()
        }

        edt_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if(!Validacoes.isEmailValid(p0.toString())){
                    edt_email.error = "Email inválido"
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        PreferencesService(this).getStringSharedPreferenceByTag(resources.getString(R.string.token))
        btn_entrar.setOnClickListener {
            showProgress()
            if(ConnectionService.test(this)){
                val controller = LoginScreenController(this, AuthIntractor())
                controller.doLogin(LoginRequest(edt_email.text.toString(), edt_senha.text.toString()))
            }else{
                hideProgress()
                makeSnackBar("Nenhuma conexão com a internet")
            }
        }

        btn_quero_cadastrar.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
    }

    override fun showProgress() {
        progress_bar_login.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar_login.visibility = View.GONE
    }

    override fun sendToMainActivity(token: String) {
        //Salva o token antes de entrar
        PreferencesService(this)
                .saveStringPreference(resources.getString(R.string.token), token)
        startActivity(Intent(this, MainActivity::class.java)
                .putExtra(TAG_MESSAGE, "Bem-vindo(a)"))
        finish()
    }

    override fun makeSnackBar(texto: String) {
        Snackbar.make(container_login, texto, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun onResponseFailure(throwable: Throwable) {
        makeSnackBar(throwable.message.toString())
    }

    companion object {
        const val TAG_MESSAGE = "MESSAGE"
    }
}
