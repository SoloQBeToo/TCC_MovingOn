package com.example.movingonteste.cliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.Initial
import com.example.movingonteste.databinding.ActivityRegisterClienteBinding
import com.example.movingonteste.telasCliente.InterfaceCliente
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterCliente : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterClienteBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterClienteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()


        if(auth.currentUser != null) {
            val intent = Intent(applicationContext, InterfaceCliente::class.java)
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

        binding.btnRegistrar.setOnClickListener {
            val nome = binding.txtNome.text.toString()
            val senha = binding.txtPassword.text.toString()
            val confirmSenha = binding.txtConfirmPassword.text.toString()
            val email = binding.txtEmail.text.toString()
            val confirmEmail = binding.txtConfirmEmail.text.toString()


            if (TextUtils.isEmpty(nome)) {
                Toast.makeText(
                    applicationContext,
                    "Campo 'Nome' não pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    applicationContext,
                    "Campo 'Email' não pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if(TextUtils.isEmpty(confirmEmail)){
                Toast.makeText(applicationContext, "A confirmação de email não pode ser nula", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(senha)) {
                Toast.makeText(
                    applicationContext,
                    "Campo 'Senha' não pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (TextUtils.isEmpty(confirmSenha)) {
                Toast.makeText(
                    applicationContext,
                    "A confirmação da senha é obrigatória",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (!senha.equals(confirmSenha)) {
                Toast.makeText(applicationContext,"Senhas devem ser iguais", Toast.LENGTH_SHORT).show()
            } else if(!email.equals(confirmEmail)) {
                Toast.makeText(applicationContext, "Emails não conferem", Toast.LENGTH_SHORT).show()
            }else{
                registerCliente(nome, email, senha)
            }
        }
        binding.goLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginClient::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun registerCliente(nome:String,email:String,senha:String){
        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    val user: FirebaseUser? = auth.currentUser
                    val userId:String = user!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Clientes").child(userId)

                    val  hashMap:HashMap<String,String> = HashMap()
                    hashMap.put("userId",userId)
                    hashMap.put("nome",nome)
                    hashMap.put("imagemPerfil","")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                        if(it.isSuccessful){
                            val intent = Intent(applicationContext, LoginClient::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
        }
    }
}