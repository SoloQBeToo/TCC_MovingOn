package com.example.telalistagem.cliente

import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import android.os.PersistableBundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.telalistagem.InitialSceen
import com.example.telalistagem.MainActivity
import com.example.telalistagem.R
import com.example.telalistagem.cliente.telas.InterfaceCliente
import com.example.telalistagem.empresa.LoginEmpresa
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class RegistrarCliente : ComponentActivity() {
    private val auth by lazy {Firebase.auth}
    private val firestore by lazy{Firebase.firestore}
    //private val db by lazy{ DocumentReference }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            TelaListagemTheme {
                RegistrarClienteSceen(auth,firestore)
            }
        }

    }
}
@Composable
fun RegistrarClienteSceen (auth: FirebaseAuth, firestore: FirebaseFirestore) {
    val context = LocalContext.current //mudança de tela
    val focusManager = LocalFocusManager.current


    //variavéis para os inputs
    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var confirmEmail by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    //Configurações Inputs
    val validadeEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches() //Padrão de email para a variável email
    }
    val validadeConfirmEmail by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(confirmEmail)
            .matches() //Padrão de email para a variável validadeConfirmEmail
    }
    val validadePassword by derivedStateOf {
        password.length >= 8
    }
    var visibilidadeSenha by remember {
        mutableStateOf(false)
    }



    Column(
    ) {
        Row( //Parte superior
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
                .height(IntrinsicSize.Min),
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
                        .size(25.dp)
                        .clickable {
                            context.startActivity(
                                Intent(context, MainActivity::class.java)
                            )
                        }
                )
            }
            Row(
                Modifier
                    .padding(top = 0.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top
            ) {
                TextButton(
                    onClick = { context.startActivity(Intent(context, LoginCliente::class.java)) },
                    border = BorderStroke(2.dp, Color(0, 0, 0, 255)),
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 10.dp)
                ) {
                    Text(
                        "Login",
                        color = Color.Black,
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        softWrap = true
                    )
                }
            }
        }
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
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cadastrar",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                color = Color(101, 90, 194, 255)
            )
        }


        //Inputs

        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
                .padding(top = 10.dp)
                .height(380.dp),
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
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    maxLines = 1,
                    label = { Text(text = "Nome:") },
                    placeholder = { Text(text = "Nome Sobrenome") },
                    modifier = Modifier.width(350.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
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
                    value = confirmEmail,
                    onValueChange = { confirmEmail = it },
                    label = { Text(text = "Confirmar Email:") },
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
                    isError = !validadeConfirmEmail, //Erro quando a validação do email está errada
                    trailingIcon = {
                        if (confirmEmail.isNotBlank()) {
                            IconButton(onClick = { confirmEmail = "" }) {
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
                    /*Button(
                        onClick = {

                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        val user = auth.currentUser?.uid
                                        val documentReference = firestore.collection("Clientes")
                                            .document(user.toString())
                                        val cliente = mutableMapOf<String, String>()

                                        cliente["nome"] = name
                                        cliente["email"] = email


                                        documentReference.set(cliente).addOnCompleteListener {
                                            if (it.isSuccessful) {
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        InterfaceCliente::class.java
                                                    )
                                                )
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Erro ao registrar",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                }
                        },
                        Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black, Color.White),
                        enabled = validadeEmail && validadeConfirmEmail && validadePassword //validação no botão
                    ) {
                        Text(
                            text = "Registrar", fontSize = 20.sp, fontFamily = FontFamily.SansSerif
                        )
                    }*/
                }
            }
        }



    }
}




@Preview
@Composable
fun DefaultPreview() {
    TelaListagemTheme {
        RegistrarClienteSceen(Firebase.auth, Firebase.firestore)
    }
}




