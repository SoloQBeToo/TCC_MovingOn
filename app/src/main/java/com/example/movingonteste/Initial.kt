package com.example.movingonteste

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movingonteste.cliente.LoginClient
import com.example.movingonteste.databinding.ActivityInitialBinding
import com.example.movingonteste.empresa.LoginEmpresa

class Initial : AppCompatActivity() {
        private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCliente.setOnClickListener {
            val intent = Intent(applicationContext, LoginClient::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnEmpresa.setOnClickListener {
            val intent = Intent(applicationContext, LoginEmpresa::class.java)
            startActivity(intent)
            finish()
        }

        binding.backImg.setOnClickListener{
            System.exit(0)
        }
        binding.txtBack.setOnClickListener {
            System.exit(0)
        }
    }
}