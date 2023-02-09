package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityMainBinding
import com.surya_yasa_antariksa.e_yadnya.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonDaftar.setOnClickListener {
            startActivity(Intent(this, DaftarAccountActivity::class.java))
            finish()
        }

        auth = FirebaseAuth.getInstance()
        binding.buttonMasuk.setOnClickListener {
            var email = binding.clmEmail.text.toString()
            var password = binding.clmPassword.text.toString()

            if (email.isEmpty()){
                binding.clmEmail.error = "Email Harus Diisi"
                binding.clmEmail.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.clmEmail.error = "Email Tidak Valid"
                binding.clmEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.clmPassword.error = "Password Harus Diisi"
                binding.clmPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.clmPassword.error = "Password Minimal 6 Karakter"
                binding.clmPassword.requestFocus()
                return@setOnClickListener
            }

            loginUserFirebase(email, password)
        }
    }

    private fun loginUserFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "$email berhasil login", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    val preferences = getSharedPreferences("login", MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putBoolean("isLoggedIn", true)
                    editor.apply()

                    finish()
                }else{
                    Toast.makeText(this, "invalid email $email", Toast.LENGTH_SHORT).show()
                }
            }
    }
}