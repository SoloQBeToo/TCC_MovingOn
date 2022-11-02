package com.example.movingonteste.telasCliente


import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movingonteste.R
import com.example.movingonteste.adapter.Adapter
import com.example.movingonteste.databinding.FragmentClienteChatBinding
import com.example.movingonteste.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_cliente_chat.*


class cliente_chat : Fragment() {

    private lateinit var binding: FragmentClienteChatBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var usuarioList: ArrayList<Usuario>
    private var db = Firebase.firestore
    private lateinit var clienteListagemLayoutManager: RecyclerView.LayoutManager
    private lateinit var usuarioAdapter: Adapter
    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClienteChatBinding.inflate(inflater, container, false)
        val root: View = binding.root
        /*
        clienteListagem = root.findViewById(R.id.listagemCliente)
        clienteListagemLayoutManager = LinearLayoutManager(context as Activity)


        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("Clientes").get().addOnSuccessListener {
            if (!it.isEmpty){
                val listarUsuario = it.documents
                for(i in listarUsuario){
                    if(i.id.equals(firebaseAuth.currentUser?.uid)){
                        Toast.makeText(context,"Erro ao receber dados",Toast.LENGTH_SHORT).show()
                    }else{
                        val user = Usuario(i.getString("userEmail").toString(),i.getString("userName").toString())

                        usuarioList.add(user)
                       // usuarioAdapter = UsuarioAdapter(context as Activity,usuarioList)
                        clienteListagem.adapter = usuarioAdapter
                        clienteListagem.layoutManager = clienteListagemLayoutManager
                        clienteListagem.addItemDecoration(DividerItemDecoration(clienteListagem.context,(clienteListagemLayoutManager as LinearLayoutManager).orientation))

                    }
                }
            }
        }*/
        return root
    }




}
