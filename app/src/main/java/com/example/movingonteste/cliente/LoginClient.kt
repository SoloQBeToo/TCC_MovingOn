package com.example.movingonteste.cliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.Initial
import com.example.movingonteste.databinding.ActivityLoginClienteBinding
import com.example.movingonteste.telas.Interface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.childEvents
import com.google.firebase.database.ktx.snapshots

class LoginClient : AppCompatActivity() {


    private lateinit var binding: ActivityLoginClienteBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginClienteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Clientes")

        binding.btnRegistrar.setOnClickListener {
            val intent = Intent(applicationContext, RegisterCliente::class.java)
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



            if(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)){
                Toast.makeText(applicationContext, "Os campos n??o podem estar vazios", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,senha)
                    .addOnCompleteListener(this){
                        if(it.isSuccessful){
                            binding.txtEmail.setText("")
                            binding.txtPassword.setText("")



                            val intent = Intent(applicationContext, Interface::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Email ou senha inv??lidos", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}
