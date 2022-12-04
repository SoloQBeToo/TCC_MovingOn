package com.example.telalistagem.cliente.telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.movingon),
            contentDescription = "a",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(150.dp)
        )

        Text(
            text = "A ausência de exercícios físicos, conhecida como sedentarismo, tem se tornado cada vez mais presente na sociedade, causando diversos distúrbios na população mundial. Pessoas se tornam sedentárias por diversos motivos, um deles sendo a falta de tempo ou incentivos para a realização de tais atividades. Após pesquisas sobre o tópico, nos perguntamos...como um aplicativo pode incentivar as pessoas a terem uma vida saudável e ativa?\n" +
                    "\n" +
                    "A partir desse questionamento, surgiu o Moving On, nosso aplicativo de gerenciamento de atividades físicas.\n" +
                    "\n" +
                    "O aplicativo consiste em 3 funções principais: Gerenciamento de Cronograma, Contato com estabelecimentos para realização de atividades físicas e calculadora de Índice de Adiposidade Corporal (IAC)\n" +
                    "\n" +
                    "Boa jornada!",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            textAlign = TextAlign.Center,
            color = Color.Black,

        )
    }
}

@Preview
@Composable
fun HomeClientePrev() {
    HomeCliente()
}