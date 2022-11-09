package com.example.telalistagem.cliente.telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telalistagem.R

class ClienteHome : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeCliente()
        }
    }
}

@Composable
fun HomeCliente(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .wrapContentSize(Alignment.Center)
    ) {
        Image(
            painter = painterResource(id = R.drawable.movingon),
            contentDescription = "a",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(125.dp)
                .width(125.dp)
        )
    }
}

@Preview
@Composable
fun HomeClientePrev() {
    HomeCliente()
}