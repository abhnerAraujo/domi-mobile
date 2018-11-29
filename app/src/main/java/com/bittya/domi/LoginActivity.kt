package com.bittya.domi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.bittya.domi.contracts.LoginScreenContract
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
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendToMainActivity(token: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun makeSnackBar(texto: String) {
        Snackbar.make(container_login, texto, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun onResponseFailure(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val TAG_MESSAGE = "MESSAGE"
    }
}
