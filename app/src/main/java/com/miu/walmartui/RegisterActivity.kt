package com.miu.walmartui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miu.walmartui.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Create Account button click listener
        binding.createAccountButton.setOnClickListener {
            val firstName = binding.fname.text.toString()
            val lastName = binding.lname.text.toString()
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Create a User object with the registration data
            val user = User(firstName, lastName, username, password)

            // Return the User object to MainActivity
            val resultIntent = intent
            resultIntent.putExtra("user", user)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}