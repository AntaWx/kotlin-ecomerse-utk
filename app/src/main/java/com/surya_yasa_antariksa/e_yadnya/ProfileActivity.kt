package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityProfileBinding

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