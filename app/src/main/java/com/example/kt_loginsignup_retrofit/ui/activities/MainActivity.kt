package com.example.kt_loginsignup_retrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kt_loginsignup_retrofit.api.RetrofitInstance
import com.example.kt_loginsignup_retrofit.databinding.ActivityMainBinding
import com.example.kt_loginsignup_retrofit.models.DefaultResponce
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                ValidateUser()
            }
        }

        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private suspend fun ValidateUser() {
        val name = binding.nameEt.text.toString().trim()
        val username = binding.usernameEt.text.toString().trim()
        val email = binding.emailEt.text.toString().trim()
        val phone = binding.phoneNoEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (name.isEmpty()) {
            binding.nameEt.error = "Name Required"
            binding.nameEt.requestFocus()
        }
        if (email.isEmpty()) {
            binding.emailEt.error = "Email Required"
            binding.emailEt.requestFocus()
        }
        if (phone.isEmpty()) {
            binding.phoneNoEt.error = "Phone No Required"
            binding.phoneNoEt.requestFocus()
        }
        if (password.isEmpty()) {
            binding.passwordEt.error = "Password Required"
            binding.passwordEt.requestFocus()
        }
        if (username.isEmpty()) {
            binding.usernameEt.error = "Username Required"
            binding.usernameEt.requestFocus()
        }

        RetrofitInstance.api.createUser(
            username, name, password, email, phone
        ).enqueue(object : Callback<DefaultResponce> {
            override fun onResponse(call: Call<DefaultResponce>,response: Response<DefaultResponce>
            ) {
                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, AfterLogin::class.java))
                finish()
            }

            override fun onFailure(call: Call<DefaultResponce>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}

