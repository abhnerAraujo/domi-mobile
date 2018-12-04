package com.bittya.domi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.bittya.domi.contracts.SplashScreenContract
import com.bittya.domi.controllers.SplashScreenController
import com.bittya.domi.services.intractors.AuthIntractor
import com.bittya.domi.services.local.ConnectionService
import com.bittya.domi.services.local.PreferencesService
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), SplashScreenContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            if(ConnectionService.test(this)){
                val authorization = PreferencesService(this)
                        .getStringSharedPreferenceByTag(resources.getString(R.string.token))
                if(authorization != ""){
                    val authController = SplashScreenController(this, AuthIntractor())
                    authController.requestDataFromServer(authorization)
                }else{
                    sendToLoginActivity(null)
                }
            }else{
                makeSnackBar("Nenhuma conexão disponível")
            }
        }, 1500)

    }

    private fun makeSnackBar(message: String){
        com.google.android.material.snackbar.Snackbar.make( container_splash, message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                .setAction("Ok", null).show()

    }

    override fun sendToLoginActivity(message: String?) {
        val intent = Intent(this, LoginActivity::class.java).putExtra(LoginActivity.TAG_MESSAGE, message)
        startActivity(intent)
        finish()
    }

    override fun sendToMainActivity() {
        val intent = Intent(
                this@SplashActivity, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }

    override fun onResponseFailure(throwable: Throwable) {
        makeSnackBar(throwable.message ?: "Erro inesperado")
    }
}
