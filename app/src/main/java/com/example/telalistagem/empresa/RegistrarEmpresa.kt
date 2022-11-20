package com.example.telalistagem.empresa

import android.content.Intent
import android.os.Bundle
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import android.os.PersistableBundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
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
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.telalistagem.InitialSceen
import com.example.telalistagem.MainActivity
import com.example.telalistagem.R
import com.example.telalistagem.cliente.LoginCliente
import com.example.telalistagem.cliente.RegistrarClienteSceen
import com.example.telalistagem.cliente.telas.InterfaceCliente
import com.example.telalistagem.empresa.telas.InterfaceEmpresa
import com.example.telalistagem.ui.theme.TelaListagemTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.checkerframework.checker.units.qual.C

class RegistrarEmpresa : ComponentActivity() {
    private val auth by lazy {Firebase.auth}
    private val firestore by lazy{Firebase.firestore}
    //private val db by lazy{ DocumentReference }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            RegistrarEmpresaScreen(auth, firestore)
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun RegistrarEmpresaScreen (auth: FirebaseAuth, firestore: FirebaseFirestore) {
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
    var cnpj by remember {
        mutableStateOf("")
    }
    var rua by remember {
        mutableStateOf("")
    }
    var uf by remember {
        mutableStateOf("")
    }
    var complemento by remember {
        mutableStateOf("")
    }
    var numero by remember {
        mutableStateOf("")
    }
    var cep by remember {
        mutableStateOf("")
    }
    var cidade by remember {
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
    val validadeCnpj by derivedStateOf {
        cnpj.length != 19
    }
    val validadeUf by derivedStateOf {
        uf.length != 3

    }
    val validadeCep by derivedStateOf {
        cep.length != 10
    }

    /*if (auth.currentUser != null){
        context.startActivity(Intent(context, InterfaceEmpresa::class.java))
    }*/

    Column(
    ) {
        Row( //Parte superior
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
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
                                Intent(context, MainActivity::class.java)
                            )
                        }
                )
            }
        }
        /*Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(75.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.movingon),
                contentDescription = "Logo",
                Modifier
                    .fillMaxSize(),
            )
        }*/
        Row(
            Modifier
                .fillMaxWidth()
                .padding(all = 10.dp)
                .height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Cadastrar",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
                color = Color(101, 90, 194, 255)
            )
        }


        //Inputs
        Box(Modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp)
            .height(IntrinsicSize.Max),) {
            Card(
                shape = RoundedCornerShape(15.dp),
                backgroundColor = Color(230, 230, 233, 255),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Column(
                    Modifier
                        .height(IntrinsicSize.Min)
                        .padding(all = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Column {
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
                    }
                    Column {
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
                            isError = (!validadeEmail) && (email === confirmEmail), //Erro quando a validação do email está errada
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
                    }
                    Column {
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
                            isError = ((!validadeConfirmEmail) && ((email.equals(confirmEmail)))), //Erro quando a validação do email está errada
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
                    }
                    Column {
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
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusManager.moveFocus(FocusDirection.Down) }
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
                    }

                    Column {
                        OutlinedTextField(
                            value = cnpj,
                            onValueChange = { cnpj = it },
                            label = { Text(text = "CNPJ:") },
                            singleLine = true,
                            maxLines = 1,
                            placeholder = { Text(text = "XX.XXX.XXX/XXXX-XX") },
                            modifier = Modifier.width(350.dp),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext =
                                { focusManager.moveFocus(FocusDirection.Down) }

                            ),
                            isError = (!validadeCnpj), //Erro quando a validação do cnpj está errada
                            trailingIcon = {
                                if (cnpj.isNotBlank() && (cnpj.length >= 19)) {
                                    IconButton(onClick = { cnpj = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = "Limpar caixa de texto"
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = rua,
                                onValueChange = { rua = it },
                                label = { Text(text = "Rua:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "Nome da sua rua aqui") },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(200.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext =
                                    { focusManager.moveFocus(FocusDirection.Next) }
                                ),
                            )
                            OutlinedTextField(
                                value = numero,
                                onValueChange = { numero = it },
                                label = { Text(text = "Número:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "190") },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(150.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext =
                                    { focusManager.moveFocus(FocusDirection.Next) }
                                ),
                            )
                        }
                    }
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = complemento,
                                onValueChange = { complemento = it },
                                label = { Text(text = "Complemento:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "Apto. 22A") },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(165.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext =
                                    { focusManager.moveFocus(FocusDirection.Left) }
                                ),
                            )
                            OutlinedTextField(
                                value = cep,
                                onValueChange = { cep = it },
                                label = { Text(text = "CEP:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "XXXXX-XXX") },
                                modifier = Modifier.width(175.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext =
                                    { focusManager.moveFocus(FocusDirection.Next) }

                                ),
                                isError = (!validadeCep), //Erro quando a validação do cnpj está errada
                                trailingIcon = {
                                    if (cep.isNotBlank() && (cep.length >= 11)) {
                                        IconButton(onClick = { cep = "" }) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "Limpar caixa de texto"
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = cidade,
                                onValueChange = { cidade = it },
                                label = { Text(text = "Cidade:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "São Paulo") },
                                modifier = Modifier
                                    .padding(5.dp)
                                    .width(235.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext =
                                    { focusManager.moveFocus(FocusDirection.Left) }
                                ),
                            )
                            OutlinedTextField(
                                value = uf,
                                onValueChange = { uf = it },
                                label = { Text(text = "UF:") },
                                singleLine = true,
                                maxLines = 1,
                                placeholder = { Text(text = "BA") },
                                modifier = Modifier.width(175.dp),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone =
                                    { focusManager.clearFocus() }

                                ),
                                isError = (!validadeUf), //Erro quando a validação do cnpj está errada
                                trailingIcon = {
                                    if (uf.isNotBlank() && (uf.length >= 4)) {
                                        IconButton(onClick = { uf = ""}) {
                                            Icon(
                                                imageVector = Icons.Filled.Clear,
                                                contentDescription = "Limpar caixa de texto"
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                    Row(
                        Modifier
                            .padding(top = 20.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                auth.createUserWithEmailAndPassword(email, password)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val user = auth.currentUser?.uid
                                                val documentReference = firestore.collection("Empresas")
                                                    .document(user.toString())
                                                val empresa = mutableMapOf<String, String>()

                                                empresa["nome"] = name
                                                empresa["email"] = email
                                                empresa["cnpj"] = cnpj
                                                empresa["cidade"] = cidade
                                                empresa["uf"] = uf
                                                empresa["rua"] = rua
                                                empresa["numero"] = numero
                                                empresa["cep"] = cep

                                                documentReference.set(empresa).addOnCompleteListener {
                                                    if (it.isSuccessful) {
                                                        context.startActivity(
                                                            Intent(
                                                                context,
                                                                InterfaceEmpresa::class.java
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
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                Color.White
                            ),
                            enabled = validadeEmail && validadeConfirmEmail && validadePassword && validadeCnpj//validação no botão
                        ) {
                            Text(
                                text = "Registrar",
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }
                }

            }
        }

    }

    Row(
        Modifier
            .padding(top = 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        TextButton(
            onClick = { context.startActivity(Intent(context, LoginEmpresa::class.java)) },
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






@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TelaListagemTheme {
        RegistrarEmpresaScreen(auth = FirebaseAuth.getInstance(), firestore = FirebaseFirestore.getInstance())
    }
}







