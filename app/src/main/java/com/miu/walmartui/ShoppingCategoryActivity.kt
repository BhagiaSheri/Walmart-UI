package com.miu.walmartui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miu.walmartui.databinding.ActivityMainBinding
import com.miu.walmartui.databinding.ActivityShoppingCategoryBinding

class ShoppingCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val username = intent.getStringExtra("username")
        binding.weclomeTV.text = "Welcome, $username"

        binding.dressIV.setOnClickListener {
            showToast("You have chosen the Clothing category of shopping")
        }

        binding.lipstickIV.setOnClickListener {
            showToast("You have chosen the Lipstick category of shopping")
        }

        binding.electronics.setOnClickListener {
            showToast("You have chosen the Electronics category of shopping")
        }

        binding.chocolateIV.setOnClickListener {
            showToast("You have chosen the Chocolates category of shopping")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}