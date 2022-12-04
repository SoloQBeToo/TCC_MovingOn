package com.example.telalistagem.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.telalistagem.model.User
import com.example.telalistagem.sealed.DataSealed
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelEmpresa : ViewModel() {

    val response: MutableState<DataSealed> = mutableStateOf(DataSealed.Vazio)

    init {
        dadosFirebase()
    }

    private fun dadosFirebase() {
        val listEmpresa = mutableListOf<User>()
        response.value = DataSealed.Carregando


        FirebaseFirestore.getInstance().collection("Clientes").get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (data in it.documents) {
                        val usuario: User? = data.toObject(User::class.java)
                        if (usuario != null) {
                            listEmpresa.add(usuario)
                        }
                        response.value = DataSealed.Sucesso(listEmpresa)
                    }
                }
            }

    }
}