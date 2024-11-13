package com.example.apiapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiapplication.databinding.ActivityMainBinding
import com.example.apiapplication.model.data
import com.example.apiapplication.model.users
import com.example.apiapplication.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usersAdapter = UsersAdapter(emptyList()) { user ->
            val intent = Intent(this@MainActivity, MainActivity2::class.java).apply {
                putExtra("name", "${user.first_name} ${user.last_name}")
                putExtra("email", user.email)
                putExtra("avatar", user.avatar)
            }
            startActivity(intent)
        }
        with(binding) {
            rvUser.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

        val client = ApiClient.getInstance().getAllUsers()
        client.enqueue(object : Callback<users> {
            override fun onResponse(call: Call<users>, response: Response<users>) {
                if (response.isSuccessful) {
                    val userList = response.body()?.data ?: emptyList()
                    usersAdapter.setData(userList) // Memperbarui data adapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<users>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}