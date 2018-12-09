package com.bittya.domi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(resources.getString(R.string.tag_domi_activities), "Abrindo activity: ${this.javaClass.simpleName}")
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            com.google.android.material.snackbar.Snackbar.make(view, "Replace with your own action", com.google.android.material.snackbar.Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_atender -> true
            R.id.action_atendimentos -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(resources.getString(R.string.tag_domi_activities), "Finalizando activity: ${this.javaClass.simpleName}")
    }
}
