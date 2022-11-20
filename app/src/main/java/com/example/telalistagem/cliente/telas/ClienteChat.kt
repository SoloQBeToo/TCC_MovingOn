package com.example.telalistagem.cliente.telas

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.telalistagem.R
import com.example.telalistagem.model.User
import com.example.telalistagem.sealed.DataSealed
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.example.telalistagem.viewmodel.ViewModelCliente

class ClienteChat : ComponentActivity() {



    val viewModel:ViewModelCliente by viewModels()
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
                    ChatCliente(viewModel)
                }
            }
        }
    }
}

@Composable
fun ChatCliente(viewModelCliente: ViewModelCliente){



    when(val  result = viewModelCliente.response.value){
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
            Text(
                text = "Empresas Registradas",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            )

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

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items(empresas){empresa->
            CardItem(empresa)
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardItem(cliente: User) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val context = LocalContext.current
    val subject= "Comunicação via aplicativo Moving On"


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp, bottom = 10.dp)
            .border(
                border = BorderStroke(2.dp, color = Color.DarkGray),
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
    ){
        Box(
            Modifier
                .background(Color(228, 228, 228, 255))
                .padding(horizontal = 10.dp)
        ) {
            Row (
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ){
                IconButton(onClick = { expanded = !expanded }
                ){
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "drop",
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_empresa),
                    contentDescription = "Icon",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(shape = CircleShape)
                        .border(2.dp, color = Color(185, 179, 179, 255), CircleShape)
                        .background(Color(202, 196, 196, 116))
                        .padding(5.dp),
                    colorFilter = ColorFilter.tint(Color(165, 165, 241, 255)
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(330.dp)
                    .padding(horizontal = 30.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
                    cliente.nome?.let { //empresa
                        Text(
                            text = it,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 5.dp)
                                .width(200.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                    /*cliente.email?.let { //empresa
                        Text(
                            text = it,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(bottom = 5.dp)
                                .width(IntrinsicSize.Max)
                            ,
                            textAlign = TextAlign.Center,
                            color = Color(82, 54, 97, 255)
                        )
                    }*/
                    cliente.cnpj?.let { //empresas
                        Text(
                            text = it,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .padding(bottom = 5.dp)
                            ,
                            textAlign = TextAlign.Center,
                            color = Color(82, 54, 97, 255),
                            maxLines = 2,
                            softWrap = true,
                            lineHeight = 1.em
                        )
                    }
                    Row {
                        cliente.rua?.let { //empresas
                            Text(
                                text = it+",",
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(bottom = 5.dp, end = 10.dp)
                                ,
                                textAlign = TextAlign.Center,
                                color = Color(82, 54, 97, 255),
                                maxLines = 2,
                                softWrap = true,
                                lineHeight = 1.em
                            )
                        }

                        cliente.numero?.let { //empresas
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(bottom = 5.dp)
                                ,
                                textAlign = TextAlign.Center,
                                color = Color(82, 54, 97, 255),
                                maxLines = 2,
                                softWrap = true,
                                lineHeight = 1.em
                            )
                        }
                    }
                    Row {
                        cliente.cidade?.let { //empresas
                            Text(
                                text = it+",",
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(bottom = 5.dp, end = 10.dp)
                                ,
                                textAlign = TextAlign.Center,
                                color = Color(82, 54, 97, 255),
                                maxLines = 2,
                                softWrap = true,
                                lineHeight = 1.em
                            )
                        }
                        cliente.uf?.let { //empresas
                            Text(
                                text = it,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .width(IntrinsicSize.Max)
                                    .padding(bottom = 1.5.dp)
                                ,
                                textAlign = TextAlign.Center,
                                color = Color(82, 54, 97, 255),
                                maxLines = 2,
                                softWrap = true,
                                lineHeight = 1.em
                            )
                        }
                    }
                    cliente.cep?.let { //empresas
                        Text(
                            text = it,
                            fontSize = 15.sp,
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                                .padding(bottom = 5.dp)
                            ,
                            textAlign = TextAlign.Center,
                            color = Color(82, 54, 97, 255),
                            maxLines = 2,
                            softWrap = true,
                            lineHeight = 1.em
                        )
                    }
                }


            }//
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,

                ) {
                if (expanded) {
                    Button(
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            Color.Black,
                            contentColor = Color.White
                        ),
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_SENDTO,
                                    Uri.parse(
                                        "mailto:${cliente.email}:?subject="+subject)
                                )

                            )

                        },
                        shape = CircleShape
                    ) {
                        Text(text = "Contatar")
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CardItemPrev() {
    CardItem(cliente = User
        (nome = "Nome",
        "email@email.com",
        "01.768.411/0001-94",
        "03693-060",
        "191",
        "Rua Girolamo Cardano",
        "SP",
        "São Paulo")
    )
}
