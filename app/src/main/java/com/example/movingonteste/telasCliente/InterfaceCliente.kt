package com.example.movingonteste.telasCliente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.movingonteste.Initial
import com.example.movingonteste.R
import com.example.movingonteste.databinding.ActivityInterfaceclienteBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class InterfaceCliente : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityInterfaceclienteBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInterfaceclienteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setSupportActionBar(binding.telaToolbar.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerCliente
        val navView: NavigationView = binding.navCliente
        val navController = findNavController(R.id.conteudoTelas)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.cliente_home, R.id.cliente_mapa, R.id.cliente_chat, R.id.cliente_cronograma, R.id.cliente_calculadora
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    fun onGroupItemClick(item: MenuItem) {
       Firebase.auth.signOut().apply {
           val intent = Intent(applicationContext, Initial::class.java)
           startActivity(intent)
           finish()
       }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.conteudoTelas)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}