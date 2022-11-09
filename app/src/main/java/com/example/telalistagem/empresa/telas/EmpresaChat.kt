package com.example.telalistagem.empresa.telas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telalistagem.R
import com.example.telalistagem.model.User
import com.example.telalistagem.sealed.DataSealed
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.example.telalistagem.viewmodel.ViewModelEmpresa

class EmpresaChat : ComponentActivity() {


 val viewModel: ViewModelEmpresa by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           TelaListagemTheme {
               Column {
                   TopAppBar(
                       title = {
                           Text(text = "Empresas Registradas")
                       },
                       backgroundColor = Color(134, 133, 133, 255),
                       contentColor = Color.Black
                   )
                   ChatEmpresa(viewModel)
               }
           }
        }
    }
}

@Composable
fun ChatEmpresa(viewModelEmpresa: ViewModelEmpresa){
    when(val  result = viewModelEmpresa.response.value){
        is DataSealed.Carregando->{
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
            }
        }
        is DataSealed.Falha->{
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = result.message,
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
            }
        }
        is DataSealed.Sucesso->{
            ShowLazyList(result.data)
        }
        else ->{
            Box(
                Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Erro ao carregar dados",
                    fontSize = MaterialTheme.typography.h5.fontSize
                )
            }
        }
    }
}

@Composable
fun ShowLazyList(empresas: MutableList<User>) {
    LazyColumn{
        items(empresas){empresa->
            CardItem(empresa)
        }
    }
}

@Composable
fun CardItem(empresa: User) {
    Spacer(modifier = Modifier.size(15.dp))
    Card(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .border(
                border = BorderStroke(2.dp, color = Color.DarkGray),
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ){
        Box(
            Modifier
                .fillMaxSize()
                .background(Color(228, 228, 228, 255))
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_empresa),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterStart)
                    .clip(shape = CircleShape)
                    .border(2.dp, color = Color(185, 179, 179, 255), CircleShape)
                    .background(Color(202, 196, 196, 116))
                    .padding(5.dp),
                colorFilter = ColorFilter.tint(Color(165, 165, 241, 255))

            )
                empresa.nome?.let {
                    Text(
                        text = it,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
                empresa.cnpj?.let {
                    Text(
                        text = it,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(10.dp),
                        textAlign = TextAlign.Center,
                        color = Color(82, 54, 97, 255)
                    )
                }
            }
        }
    }
