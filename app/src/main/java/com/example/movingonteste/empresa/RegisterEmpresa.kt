package com.example.movingonteste.empresa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.Initial
import com.example.movingonteste.cliente.LoginClient
import com.example.movingonteste.databinding.ActivityRegisterEmpresaBinding
import com.example.movingonteste.telasCliente.InterfaceCliente
import com.example.movingonteste.telasEmpresa.InterfaceEmpresa
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RegisterEmpresa : AppCompatActivity() {

        private lateinit var binding: ActivityRegisterEmpresaBinding
        private lateinit var auth: FirebaseAuth
        private lateinit var documentReference : DocumentReference
        private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEmpresaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()


        if(auth.currentUser != null){
            val intent = Intent(applicationContext, InterfaceEmpresa::class.java)
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
            val endereco = binding.txtEndereco.text.toString()
            val cep = binding.txtCep.text.toString()
            val uf = binding.txtUf.text.toString()

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
            if(TextUtils.isEmpty(endereco)){
                Toast.makeText(applicationContext, "Endereço é obrigatório", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(cep)){
                Toast.makeText(applicationContext, "CEP é obrigatório", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(uf)){
                Toast.makeText(applicationContext, "UF é obrigatório", Toast.LENGTH_SHORT).show()
            }
            if (!senha.equals(confirmSenha)) {
                Toast.makeText(applicationContext,"Senhas devem ser iguais", Toast.LENGTH_SHORT).show()
            } else if(!email.equals(confirmEmail)) {
                Toast.makeText(applicationContext, "Emails não conferem", Toast.LENGTH_SHORT).show()
            }else{
                registrarEmpresa(nome, email, senha)
            }
        }
        binding.goLogin.setOnClickListener {
            val intent = Intent(applicationContext, LoginEmpresa::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun registrarEmpresa(nome: String, email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser?.uid
                    documentReference = firestore.collection("Empresas").document(user.toString())
                    val empresa: HashMap<String, String> = HashMap()
                    empresa.put("userId", user.toString())
                    empresa.put("nome", nome)
                    empresa.put("imagemPerfil", "")

                    documentReference.set(empresa).addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val intent = Intent(applicationContext, LoginEmpresa::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "Erro ao registrar", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }
}