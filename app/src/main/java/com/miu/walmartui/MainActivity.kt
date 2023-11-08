package com.miu.walmartui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.miu.walmartui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val users = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Add some sample users to the ArrayList for Login
        users.add(User("Bhagia", "Sheri", "bhagia.sheri@gmail.com", "bhagia123"))
        users.add(User("John", "Doe", "john@gmail.com", "password123"))
        users.add(User("Jane", "Smith", "jane@gmail.com", "securepass"))
        users.add(User("John", "Kapoor", "kapoor@gmail.com", "kapoor123"))
        users.add(User("Smith", "Sina", "sina@gmail.com", "sinasmith"))

        binding.signIn.setOnClickListener {
            val enteredUsername = binding.email.text.toString()
            val enteredPassword = binding.password.text.toString()

            val user =
                users.find { it.username == enteredUsername && it.password == enteredPassword }

            if (user != null) {
                val intent = Intent(this, ShoppingCategoryActivity::class.java)
                intent.putExtra("username", user.username)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.forgotPass.setOnClickListener {
            val enteredUsername = binding.email.text.toString()

            // Search for the user with the entered username
            val user = users.find { it.username == enteredUsername }

            if (user != null) {
                // Retrieve the user's password
                val userPassword = user.password

                // Send the password to the registered email using an implicit Intent
                sendPasswordByEmail(user.username, userPassword)
            } else {
                Toast.makeText(this, "User not found. Please check the username.", Toast.LENGTH_SHORT).show()
            }
        }

        val resultContracts =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
               //     val newUser: User = it.data?.data as User
                    val newUser =  it.data?.getSerializableExtra("user") as? User
                    if (newUser != null) {
                        users.add(newUser)
                    }
                    Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                }
            }

        binding.createAccOptionBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            resultContracts.launch(intent)
        }
    }

    private fun sendPasswordByEmail(email: String, password: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery")
        intent.putExtra(Intent.EXTRA_TEXT, "Your password is: $password")

        try {
            startActivity(Intent.createChooser(intent, "Send email using"))
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show()
        }
    }
}