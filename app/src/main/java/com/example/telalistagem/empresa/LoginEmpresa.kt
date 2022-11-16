package com.example.telalistagem.empresa

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telalistagem.MainActivity
import com.example.telalistagem.R
import com.example.telalistagem.empresa.telas.InterfaceEmpresa
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginEmpresa : ComponentActivity() {
    private val auth by lazy{FirebaseAuth.getInstance()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenEmpresa(auth)
        }
    }
}

@Composable
fun LoginScreenEmpresa(auth: FirebaseAuth){
    val context = LocalContext.current //mudança de tela
    val focusManager = LocalFocusManager.current

    //variavéis para os inputs

    var email by remember {
        mutableStateOf("")
    }


    var password by remember {
        mutableStateOf("")
    }

    //Configurações Inputs
    val validadeEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches() //Padrão de email para a variável email
    }

    val validadePassword by derivedStateOf {
        password.length >= 8
    }
    var visibilidadeSenha by remember {
        mutableStateOf(false)
    }


    Column() {
        Row( //Parte superior
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp)
                .height(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                Modifier
                    .width(30.dp),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Moving On",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            context.startActivity(
                                Intent(context, RegistrarEmpresa::class.java)
                            )
                        }
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp) .padding(top=20.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.movingon),
                contentDescription = "Logo",
                Modifier
                    .fillMaxSize(),
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                color = Color(101, 90, 194, 255)
            )
            Icon(imageVector = Icons.Filled.FitnessCenter,
                contentDescription = null,
                Modifier
                    .padding(16.dp), tint = Color(101, 90, 194, 255)
            )
        }
        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
                .padding(top = 10.dp)
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(230, 230, 233, 255),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(all = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email:") },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "email@email.com") },
                    modifier = Modifier.width(350.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    isError = !validadeEmail, //Erro quando a validação do email está errada
                    trailingIcon = {
                        if (email.isNotBlank()) {
                            IconButton(onClick = { email = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Limpar caixa de texto"
                                )
                            }
                        }
                    }
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Senha:") },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "Sua senha aqui:") },
                    modifier = Modifier.width(350.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    isError = !validadePassword, //Erro quando a senha não é válida
                    trailingIcon = {
                        IconButton(onClick = {
                            visibilidadeSenha = !visibilidadeSenha
                        }) { //botão para esconder/mostrar visibibilidade do campo
                            Icon(
                                imageVector = if (visibilidadeSenha) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Exibir ou não senha"
                            )
                        }
                    },
                    visualTransformation = if (visibilidadeSenha) VisualTransformation.None else PasswordVisualTransformation() //ação de esconder/exibir a senha
                )
                Row(
                    Modifier
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            auth.signInWithEmailAndPassword(email,password)
                                .addOnCompleteListener{
                                    if(it.isSuccessful){
                                        context.startActivity(Intent(context, InterfaceEmpresa::class.java))
                                    }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Email ou senha errados", Toast.LENGTH_SHORT).show()
                                }
                        },
                        Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black,
                            Color.White
                        ),
                        enabled = validadeEmail && validadePassword //validação no botão
                    ) {
                        Text(
                            text = "Fazer Login", fontSize = 20.sp, fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
    }

}


@Preview
@Composable
fun LoginEmpresaScreen() {
    TelaListagemTheme {
        LoginScreenEmpresa(Firebase.auth)
    }
}