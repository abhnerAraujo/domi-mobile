package com.bittya.domi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.util.Log
import com.bittya.domi.contracts.SplashScreenContract
import com.bittya.domi.controllers.SplashScreenController
import com.bittya.domi.services.intractors.AuthIntractor
import com.bittya.domi.services.local.ConnectionService
import com.bittya.domi.services.local.PreferencesService
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), SplashScreenContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(resources.getString(R.string.tag_domi_activities), "Abrindo activity: ${this.javaClass.simpleName}")
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            if(ConnectionService.test(this)){
                val authorization = PreferencesService(this)
                        .getStringSharedPreferenceByTag(resources.getString(R.string.token))
                if(authorization != ""){
                    val authController = SplashScreenController(this, AuthIntractor())
                    authController.validateToken(authorization)
                }else{
                    sendToLoginActivity(null)
                }
            }else{
                makeSnackBar(resources.getString(R.string.info_nenhuma_conexao_internet))
            }
        }, 1500)

    }

    private fun makeSnackBar(message: String){
        com.google.android.material.snackbar.Snackbar.make( container_splash, message, com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                .setAction("Ok", null).show()

    }

    override fun sendToLoginActivity(message: String?) {
        startActivity(Intent(this, LoginActivity::class.java).apply {
            this.putExtra(resources.getString(R.string.tag_mensagem), message)
        }).also { finish() }
    }

    override fun sendToMainActivity() =
        startActivity(Intent(this, MainActivity::class.java)).also { finish() }


    override fun onResponseFailure(throwable: Throwable) {
        makeSnackBar(throwable.message ?: "Erro inesperado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(resources.getString(R.string.tag_domi_activities), "Finalizando activity: ${this.javaClass.simpleName}")
    }
}
