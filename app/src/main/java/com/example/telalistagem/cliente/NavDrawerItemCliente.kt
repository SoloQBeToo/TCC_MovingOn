package com.example.telalistagem.cliente

import com.example.telalistagem.R

sealed class NavDrawerItemCliente(var route: String, var icon: Int, var title: String){
    //Classe contendo os itens do drawer(vCliente)
    object Home: NavDrawerItemCliente("home", R.drawable.ic_home,"Home")
    object Cronograma: NavDrawerItemCliente("cronograma", R.drawable.ic_timeline,"Timeline")
    object Chat: NavDrawerItemCliente("chat", R.drawable.ic_chat,"Chat")
    object Mapa: NavDrawerItemCliente("mapa", R.drawable.ic_map,"Mapa")
}