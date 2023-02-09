package db_helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(activity: Context) {

    val login = "login"
    val myPreference = "Main_Pref"
    val sharedPreferences : SharedPreferences

    init{
        sharedPreferences = activity.getSharedPreferences(myPreference, Context.MODE_PRIVATE)
    }

    fun setStatusLogin(status : Boolean){
        sharedPreferences.edit().putBoolean(login , status).apply()
    }

    fun getStatusLogin(): Boolean{
        return sharedPreferences.getBoolean(login , false)
    }
}