package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.surya_yasa_antariksa.e_yadnya.adapter.ViewPagerAdapter
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityHomeBinding
import com.surya_yasa_antariksa.e_yadnya.fragment.HomeFragment
import com.surya_yasa_antariksa.e_yadnya.fragment.ProfileFragment

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var actionBar = getSupportActionBar()
        when{
            actionBar != null -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.title = "home"
            }
        }

        setupTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action__bar, menu)
        return true
    }

    public override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.notification -> Toast.makeText(applicationContext, "notifikasi belum tersedia", Toast.LENGTH_SHORT).show()
            R.id.keranjang -> Toast.makeText(applicationContext, "keranjang belum tersedia", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(ProfileFragment(), "Profile")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.profile_icon)
    }
}