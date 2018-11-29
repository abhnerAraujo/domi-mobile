package com.bittya.domi.services.local

import android.content.Context

class PreferencesService(context: Context) {

    private val sharePreferenceFile = "com.bittya.domi"
    private val sharedPreferences = context.getSharedPreferences(sharePreferenceFile,
            Context.MODE_PRIVATE)


    fun getStringSharedPreferenceByTag(tag: String): String{
        return sharedPreferences.getString(tag, "")
    }

    fun getIntSharedPreferenceByTag(tag: String): Int{
        return sharedPreferences.getInt(tag, 0)
    }

    fun saveStringPreference(tag: String, value: String){
        sharedPreferences.edit().let {
            it.putString(tag, value)
            it.apply()
        }
    }

    fun saveIntegerPreference(tag: String, value: Int){
        sharedPreferences.edit().let {
            it.putInt(tag, value)
            it.apply()
        }
    }
}