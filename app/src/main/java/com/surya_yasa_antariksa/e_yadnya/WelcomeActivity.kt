package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.surya_yasa_antariksa.e_yadnya.fragment.ProfileFragment
import db_helper.SharedPreference
import kotlinx.android.synthetic.main.activity_daftar_account.*
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        supportActionBar?.hide()
        if (!checkCameraPermission()){
            requestCameraPermission()
        }
        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val isLoggedIn = preferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        IntentButtonLogin()
        IntentButtonRegister()

        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "Tidak terhubung ke internet", Toast.LENGTH_SHORT).show()
            return
        }
    }
    private fun IntentButtonLogin(){
        login_btn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            if (!isNetworkConnected(this)) {
                Toast.makeText(this, "Tidak terhubung ke internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        const val CAMERA_PERMISSION_REQUEST_CODE = 100
    }

    private fun IntentButtonRegister(){
        register_btn.setOnClickListener {
            startActivity(Intent(this, DaftarAccountActivity::class.java))
        }
    }

    private fun checkCameraPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Ijin Kamera Sudah Diberikan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Membuka Kamera Tidak Diijinkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

}