package com.example.telalistagem.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.telalistagem.model.User
import com.example.telalistagem.sealed.DataSealed
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelCliente : ViewModel() {

    val response: MutableState<DataSealed> = mutableStateOf(DataSealed.Vazio)

    init {
        dadosFirebase()
    }

    private fun dadosFirebase() {
        val listUser = mutableListOf<User>()
        response.value = DataSealed.Carregando

        FirebaseFirestore.getInstance().collection("Empresas").get()
            .addOnSuccessListener {
                if (!it.isEmpty) {
                    for (data in it.documents) {
                        val usuario: User? = data.toObject(User::class.java)
                        if (usuario != null) {
                            listUser.add(usuario)
                        }
                        response.value = DataSealed.Sucesso(listUser)
                    }
                }
            }
    }
}
