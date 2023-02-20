package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityUserSettingBinding


class UserSettingActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserSettingBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var actionBar = supportActionBar
        when{
            actionBar != null -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.title = "user setting"
            }
        }

        binding.logoutButton.setOnClickListener {
            tombolKeluar()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.user_profile_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> finish()
            R.id.home_icon -> startActivity(Intent(this, HomeActivity::class.java))
            R.id.notification -> Toast.makeText(applicationContext, "notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
            R.id.keranjang -> Toast.makeText(applicationContext, "keranjang belum tersedia", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    fun tombolKeluar(){
        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        val editor = preferences?.edit()
        editor?.putBoolean("isLoggedIn", false)
        editor?.apply()

        auth = FirebaseAuth.getInstance()
        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "Tidak terhubung ke internet", Toast.LENGTH_SHORT).show()
            return
        }
        auth.signOut()
        val i = Intent(this, WelcomeActivity::class.java)
        val toast = Toast.makeText(this, "Berhasil Log Out", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
        startActivity(i)
        finish()
    }
}