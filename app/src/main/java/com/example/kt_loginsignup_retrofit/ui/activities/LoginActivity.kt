package com.example.kt_loginsignup_retrofit.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kt_loginsignup_retrofit.api.RetrofitInstance
import com.example.kt_loginsignup_retrofit.databinding.ActivityLoginBinding
import com.example.kt_loginsignup_retrofit.models.DefaultResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordEt.text.toString().trim()

        if (email.isEmpty()) {
            binding.emailEt.error = "Email Required"
            binding.emailEt.requestFocus()
        }
        if (password.isEmpty()) {
            binding.passwordEt.error = "Phone No Required"
            binding.passwordEt.requestFocus()
        }

        lifecycleScope.launch(Dispatchers.IO) {
            RetrofitInstance.api.logIn(email, password).enqueue(object : Callback<DefaultResponce> {
                override fun onResponse(
                    call: Call<DefaultResponce>,
                    response: Response<DefaultResponce>
                ) {
                    
                }

                override fun onFailure(call: Call<DefaultResponce>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, t.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }

            })
        }
    }
}