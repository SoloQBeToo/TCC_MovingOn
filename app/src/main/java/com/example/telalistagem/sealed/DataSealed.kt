package com.example.telalistagem.sealed

import com.example.telalistagem.model.User

sealed class DataSealed {
    class Sucesso(val data:MutableList<User>) : DataSealed()
    class Falha(val message:String) : DataSealed()
    object Carregando : DataSealed()
    object Vazio : DataSealed()

}