package com.example.telalistagem.empresa.telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.telalistagem.R


import com.example.telalistagem.empresa.NavDrawerItemEmpresa
import com.example.telalistagem.viewmodel.ViewModelEmpresa

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class InterfaceEmpresa : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavEmpresa()
        }
    }
}
@Composable
fun NavEmpresa() {
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))//Drawer fechado inicialmente
    val scope = rememberCoroutineScope()//Ciclo de vida do drawer
    val navController = rememberNavController()//NavController

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerBackgroundColor = Color(99, 7, 7, 255),
        drawerContent = {
            DrawerEmpresa(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        backgroundColor = Color(253, 252, 252, 255)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)){
            NavigationEmpresa(navController = navController)
        }
    }
}
@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = MaterialTheme.typography.h5.fontSize
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch { scaffoldState.drawerState.open() } //abre o drawer
                }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Abre drawer")
            }
        },
        backgroundColor = Color.White,
        contentColor = Color.Black
    )

}
@Composable
fun DrawerEmpresa(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){
    val items = listOf(
        NavDrawerItemEmpresa.Home,
        NavDrawerItemEmpresa.Chat,
    )
    Column(
        Modifier
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.movingon),
            contentDescription = R.drawable.movingon.toString(),
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(120.dp),
        )
        Spacer(modifier = Modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth())

        val navBackStackEntry by navController.currentBackStackEntryAsState() //Fornece acesso ao NavController atual
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{item ->
            DrawerItemEmpresa(item = item, selected = currentRoute == item.route, onItemClick = {
                navController.navigate(item.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()//fecha drawer
                }
            })
        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Moving On",
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(15.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun DrawerItemEmpresa(item: NavDrawerItemEmpresa, selected: Boolean, onItemClick: (NavDrawerItemEmpresa) -> Unit){
    val background = if(selected) R.color.purple_200 else R.color.transparent

    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(65.dp)
            .background(colorResource(id = background))
            .padding(start = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = item.title, fontSize = 20.sp, color = Color.Black)
    }
}
@Composable
fun NavigationEmpresa(navController: NavHostController){
    NavHost(navController, startDestination = NavDrawerItemEmpresa.Home.route){
        composable(NavDrawerItemEmpresa.Home.route){
            HomeEmpresa()
        }
        composable(NavDrawerItemEmpresa.Chat.route){
            ChatEmpresa(viewModelEmpresa = ViewModelEmpresa())
        }
    }
}
