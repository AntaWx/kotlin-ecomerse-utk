package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.surya_yasa_antariksa.e_yadnya.fragment.ProfileFragment
import db_helper.SharedPreference
import kotlinx.android.synthetic.main.activity_daftar_account.*
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()

        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val isLoggedIn = preferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        IntentButtonLogin()
        IntentButtonRegister()
    }
    private fun IntentButtonLogin(){
        login_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun IntentButtonRegister(){
        register_btn.setOnClickListener {
            startActivity(Intent(this, DaftarAccountActivity::class.java))
        }
    }
}