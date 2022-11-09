package com.example.telalistagem.empresa

import com.example.telalistagem.R

sealed class NavDrawerItemEmpresa(var route: String, var icon: Int, var title: String){
    //Classe contendo os itens do drawer(vEmpresa)
    object Home: NavDrawerItemEmpresa("home", R.drawable.ic_home,"Home")
    object Chat: NavDrawerItemEmpresa("chat", R.drawable.ic_chat,"Chat")

}