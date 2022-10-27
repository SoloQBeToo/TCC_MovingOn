package com.example.movingonteste.cliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.databinding.ActivityLoginClienteBinding
import com.example.movingonteste.telasCliente.InterfaceCliente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

class LoginClient : AppCompatActivity() {


    private lateinit var binding: ActivityLoginClienteBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginClienteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()


        if(auth.currentUser != null) {
            val intent = Intent(applicationContext, InterfaceCliente::class.java)
            startActivity(intent)
            finish()
        }


        binding.backImg.setOnClickListener {
            val intent = Intent(applicationContext, RegisterCliente::class.java)
            startActivity(intent)
            finish()
        }
        binding.txtBack.setOnClickListener {
            val intent = Intent(applicationContext, RegisterCliente::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val senha = binding.txtPassword.text.toString()



            if(TextUtils.isEmpty(email) && TextUtils.isEmpty(senha)){
                Toast.makeText(applicationContext, "Os campos não podem estar vazios", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email,senha)
                    .addOnCompleteListener(this){
                        if(it.isSuccessful){
                            binding.txtEmail.setText("")
                            binding.txtPassword.setText("")



                            val intent = Intent(applicationContext, InterfaceCliente::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Email ou senha inválidos", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }
}
