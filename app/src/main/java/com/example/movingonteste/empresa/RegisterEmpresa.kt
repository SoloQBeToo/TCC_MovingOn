package com.example.movingonteste.empresa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.movingonteste.R
import com.example.movingonteste.cliente.LoginClient
import com.example.movingonteste.databinding.ActivityRegisterEmpresaBinding
import com.example.movingonteste.telas.Interface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterEmpresa : AppCompatActivity() {

        private lateinit var binding: ActivityRegisterEmpresaBinding
        private lateinit var auth: FirebaseAuth
        private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEmpresaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.backImg.setOnClickListener {
            val intent = Intent(applicationContext, LoginEmpresa::class.java)
            startActivity(intent)
            finish()
        }
        binding.txtBack.setOnClickListener {
            val intent = Intent(applicationContext, LoginEmpresa::class.java)
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
                    "Campo 'Nome' n??o pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(
                    applicationContext,
                    "Campo 'Email' n??o pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if(TextUtils.isEmpty(confirmEmail)){
                Toast.makeText(applicationContext, "A confirma????o de email n??o pode ser nula", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(senha)) {
                Toast.makeText(
                    applicationContext,
                    "Campo 'Senha' n??o pode ser nulo",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (TextUtils.isEmpty(confirmSenha)) {
                Toast.makeText(
                    applicationContext,
                    "A confirma????o da senha ?? obrigat??ria",
                    Toast.LENGTH_SHORT
                ).show()
            }
            if(TextUtils.isEmpty(endereco)){
                Toast.makeText(applicationContext, "Endere??o ?? obrigat??rio", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(cep)){
                Toast.makeText(applicationContext, "CEP ?? obrigat??rio", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(uf)){
                Toast.makeText(applicationContext, "UF ?? obrigat??rio", Toast.LENGTH_SHORT).show()
            }
            if (!senha.equals(confirmSenha)) {
                Toast.makeText(applicationContext,"Senhas devem ser iguais", Toast.LENGTH_SHORT).show()
            } else if(!email.equals(confirmEmail)) {
                Toast.makeText(applicationContext, "Emails n??o conferem", Toast.LENGTH_SHORT).show()
            }else{
                registerEmpresa(nome, email, senha)
            }
        }

    }
    private fun registerEmpresa(nome:String,email:String,senha:String){
        auth.createUserWithEmailAndPassword(email,senha)
            .addOnCompleteListener(this) {
                if(it.isSuccessful){
                    val empresa: FirebaseUser? = auth.currentUser
                    val empresaId:String = empresa!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Empresas").child(empresaId)

                    val  hashMap:HashMap<String,String> = HashMap()
                    hashMap.put("empresaId",empresaId)
                    hashMap.put("nome",nome)
                    hashMap.put("imagemPerfil","")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this) {
                        if(it.isSuccessful){
                            val intent = Intent(applicationContext, Interface::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
    }
}