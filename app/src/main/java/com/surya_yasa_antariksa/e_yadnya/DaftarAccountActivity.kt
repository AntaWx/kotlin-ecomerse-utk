package com.surya_yasa_antariksa.e_yadnya

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.surya_yasa_antariksa.e_yadnya.databinding.ActivityDaftarAccountBinding
import java.util.regex.Pattern

class DaftarAccountActivity : AppCompatActivity() {

    lateinit var binding: ActivityDaftarAccountBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        binding.buttonDaftar.setOnClickListener {

            var email = binding.columnEmail.text.toString()
            var password = binding.columnPassword.text.toString()

            if (email.isEmpty()){
                binding.columnEmail.error = "Email Harus Diisi"
                binding.columnEmail.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.columnEmail.error = "Email Tidak Valid"
                binding.columnEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.columnPassword.error = "Password Harus Diisi"
                binding.columnPassword.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 6){
                binding.columnPassword.error = "Password Minimal 6 Karakter"
                binding.columnPassword.requestFocus()
                return@setOnClickListener
            }

            daftarUserFirebase(email, password)
        }
        binding.textMasuk.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun daftarUserFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}