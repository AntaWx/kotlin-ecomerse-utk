package com.surya_yasa_antariksa.e_yadnya

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat.getSystemService
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityProfileBinding
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat

class ProfileActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var actionBar = getSupportActionBar()
        when{
            actionBar != null -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.title = "profile"
            }
        }

        if (!isNetworkConnected(this)) {
            Toast.makeText(this, "Tidak terhubung ke internet", Toast.LENGTH_SHORT).show()
            return
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.kotlin_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            androidx.appcompat.R.id.home -> startActivity(Intent(this, HomeActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}

fun isNetworkConnected(context: Context): Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}