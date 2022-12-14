package com.example.movingonteste.empresa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.Initial
import com.example.movingonteste.R
import com.example.movingonteste.cliente.RegisterCliente
import com.example.movingonteste.databinding.ActivityLoginEmpresaBinding
import com.example.movingonteste.telas.Interface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginEmpresa : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEmpresaBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEmpresaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        auth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(applicationContext, RegisterEmpresa::class.java)
            startActivity(intent)
            finish()
        }
        binding.backImg.setOnClickListener {
            val intent = Intent(applicationContext, Initial::class.java)
            startActivity(intent)
            finish()
        }
        binding.txtBack.setOnClickListener {
            val intent = Intent(applicationContext, Initial::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val senha = binding.txtPassword.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)) {
                Toast.makeText(
                    applicationContext,
                    "Os campos não podem estar vazios",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                auth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            binding.txtEmail.setText("")
                            binding.txtPassword.setText("")

                            databaseReference = FirebaseDatabase.getInstance().getReference("Empresas")
                            val intent = Intent(applicationContext, Interface::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(
                                applicationContext,
                                "Email ou senha inválidos",
                                Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

