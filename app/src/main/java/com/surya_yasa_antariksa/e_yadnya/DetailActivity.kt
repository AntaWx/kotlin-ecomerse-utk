package com.surya_yasa_antariksa.e_yadnya

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityDetailBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var actionBar = supportActionBar
        when{
            actionBar != null -> {
                actionBar.title = "Detail Pesanan"
//                actionBar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFFFF")))
        }
        }

        val now = LocalDateTime.now()
        val dateTime =  now.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm"))
        val perjalananText = findViewById<TextView>(R.id.perjalanan_text)
        perjalananText.text = "${getString(R.string.sampai)} $dateTime"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_action_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }
}