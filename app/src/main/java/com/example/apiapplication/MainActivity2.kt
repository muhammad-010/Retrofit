package com.example.apiapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.apiapplication.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val name = intent.getStringExtra("name")
            val email = intent.getStringExtra("email")
            val avatar = intent.getStringExtra("avatar")

            txtName.text = name
            txtEmail.text = email
            Glide.with(this@MainActivity2).load(avatar).into(binding.image)

            image.setOnClickListener {
                val intent = Intent(this@MainActivity2, MainActivity::class.java).apply {
                }
                startActivity(intent)
            }
        }
    }
}