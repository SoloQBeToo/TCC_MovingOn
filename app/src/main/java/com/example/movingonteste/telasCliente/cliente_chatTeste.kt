package com.example.movingonteste.telasCliente

import android.os.Bundle
import com.example.movingonteste.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movingonteste.adapter.Adapter
import com.example.movingonteste.databinding.ActivityClienteChatTesteBinding
import com.example.movingonteste.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cliente_chat_teste.*
import kotlinx.android.synthetic.main.listagem_user.*


class cliente_chatTeste : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var usuarioList: ArrayList<Usuario>
    private var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente_chat_teste)



    recyclerView = findViewById(R.id.listagemCliente1)
    recyclerView.layoutManager = LinearLayoutManager(this)

    usuarioList = arrayListOf()


    db = FirebaseFirestore.getInstance()

    db.collection("Clientes").get()
        .addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents){
                    val usuario: Usuario? = data.toObject(Usuario::class.java)
                    if (usuario != null) {
                        usuarioList.add(usuario)
                    }
                    recyclerView.adapter = Adapter(usuarioList)
                }

            }

        }
        .addOnFailureListener {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }
}

/*private fun getUserList() {

    /*val firebase: FirebaseUser = FirebaseAuth.getInstance().currentUser!!
    val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Empresas")

    databaseReference.addValueEventListener(object : ValueEventListener{
        override fun onCancelled(error: DatabaseError) {
           Toast.makeText(applicationContext,error.message,Toast.LENGTH_SHORT).show()
        }
        override fun onDataChange(snapshot: DataSnapshot) {
            listagem.clear()
            val usuarioLogado = snapshot.getValue(Usuario::class.java)
            if (usuarioLogado!!.userImage == ""){
                userImage.setImageResource(R.drawable.common_google_signin_btn_text_dark)
            }else{
                Glide.with(this@cliente_chatTeste).load(usuarioLogado.userImage).into(userImage)
            }

            for (dataSnapShot : DataSnapshot in snapshot.children){
                val usuario = dataSnapShot.getValue(Usuario::class.java)

                if(!usuario!!.userId.equals(firebase.uid)){
                    listagem.add(usuario)
                }
            }
            val usuarioAdapter = UsuarioAdapter(this@cliente_chatTeste,listagem)

            listagemCliente.adapter = usuarioAdapter
        }

    })*/

        }

    /*db.collection("Clientes")
        .get()
        .addOnSuccessListener {
            val adapter =

            for (document in it){
                val user = it.toObjects(Usuario::class.java)

            }

        }*/




}*/
}








