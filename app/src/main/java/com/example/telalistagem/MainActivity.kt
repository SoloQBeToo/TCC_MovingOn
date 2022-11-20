package com.example.telalistagem
import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Space
import android.widget.Toolbar
import androidx.compose.ui.Modifier
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Pin
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telalistagem.cliente.RegistrarCliente
import com.example.telalistagem.empresa.RegistrarEmpresa
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalPermissionsApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InitialSceen()
            }

        }
    }




@Composable
fun InitialSceen() {

    val context = LocalContext.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
    ) {

        Column(
            modifier = Modifier
                .background(color = Color.LightGray)
                .padding(top = 25.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top

        ) {
            Image(
                painter = painterResource(id = R.drawable.movingon),
                contentDescription = "Moving On",
                modifier = Modifier
                    .size(140.dp)
            )

        }

        Column(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Entrar como:",
                style = MaterialTheme.typography.h4,
                color = Color(101, 90, 194, 255),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(bottom = 10.dp, top = 50.dp),
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(),
            ){
                Column(
                    modifier = Modifier

                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {


                    Button(
                        onClick = {

                            context.startActivity(Intent(context, RegistrarCliente::class.java))
                        },
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 75.dp, start = 75.dp)
                    ) {
                        Icon(
                            Icons.Filled.People, contentDescription = "",
                            modifier = Modifier.padding(end=15.dp))
                        Text(
                            text = "Cliente", fontSize = 17.sp
                        )

                    }
                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, RegistrarEmpresa::class.java))
                        },
                        modifier = Modifier
                            .padding(end = 75.dp, start = 75.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Filled.FitnessCenter, contentDescription = "",
                            modifier = Modifier.padding(end=15.dp)
                        )
                        Text(
                            text="Estabelecimento/Persoal Trainer", fontSize = 17.sp)
                    }
                }

            }

        }

        }
    }









@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TelaListagemTheme {
        InitialSceen()
    }
}