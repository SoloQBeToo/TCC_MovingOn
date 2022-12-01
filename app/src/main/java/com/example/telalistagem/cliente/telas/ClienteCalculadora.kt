package com.example.telalistagem.cliente.telas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.telalistagem.R
import kotlin.math.sqrt

class ClienteCalculadora :ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraCliente()
        }
    }
}
@Composable
fun CalculadoraCliente(){

    val iacExplicacao = stringResource(id = R.string.iacExplicacao)
    val iacObs = stringResource(id = R.string.iacObs)
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current //mudança de tela




    var altura by remember {
        mutableStateOf("")
    }
    var quadril by remember {
        mutableStateOf("")
    }
    var resultado by remember{
        mutableStateOf("")
    }

    val validadeQuadril by derivedStateOf {
        (quadril.length >= 2) && (quadril.length <=6)

    }
    val validadeAltura by derivedStateOf {
        (altura.length >= 2) && (altura.length <=4)
    }

    fun CalcIac(){
        val alturaDale = altura.toDouble()
        val quadrilDale = quadril.toDouble()

        val resultDale = (quadrilDale/(alturaDale * sqrt(alturaDale))) - 18

        resultado = "%.2f".format(resultDale)
    }

    fun WebIntent(){
        val webIntent: Intent = Uri.parse("https://www.android.com").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
    }


    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Calculadora de IAC",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )

        }
        Row(
            Modifier
                .width(IntrinsicSize.Max)
                .padding(start = 10.dp, end = 10.dp)
                .padding(top = 25.dp)
                .height(IntrinsicSize.Min)
        ) {
            /*Text(
                buildAnnotatedString {
                    withStyle(style = ParagraphStyle(textAlign = TextAlign.Justify)) {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        ) {
                            append("O que é o IAC:")
                        }
                    }
                    append(iacExplicacao+"\n"+"\n"+"Resultado do IAC:")
                },
            )*/
            //
            Row() {
                Text(
                    text = "O que indica o resultado:",
                    fontSize = 15.sp,

                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .padding(top = 10.dp)
                .height(IntrinsicSize.Max)
        ) {
            Column (
                Modifier
                    .fillMaxWidth()
                    .height(65.dp),
                Arrangement.Center,
            ){
                Image(
                    painterResource(id = R.drawable.pasta1_1),
                    contentDescription = "Tabela IAC",
                    Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )

                //
            }
        }



        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp)
                .padding(top = 10.dp)
                .height(IntrinsicSize.Min),
            shape = RoundedCornerShape(15.dp),
            backgroundColor = Color(230, 230, 233, 255),
            border = BorderStroke(1.dp, Color.Black),
            elevation = 3.dp
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(all = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )  {
                OutlinedTextField(
                    value = quadril,
                    onValueChange = { quadril = it },
                    label = { Text(text = "Quadril") },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "") },
                    modifier = Modifier.width(350.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    isError = !validadeQuadril, //Erro quando a validação do email está errada
                    trailingIcon = {
                        if (quadril.isNotBlank()) {
                            IconButton(onClick = { quadril = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Limpar caixa de texto"
                                )
                            }
                        }
                    }
                )
                OutlinedTextField(
                    value = altura,
                    onValueChange = { altura = it },
                    label = { Text(text = "Sua altura") },
                    singleLine = true,
                    maxLines = 1,
                    placeholder = { Text(text = "1.75") },
                    modifier = Modifier.width(350.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    isError = !validadeAltura, //Erro quando a validação do email está errada
                    trailingIcon = {
                        if (altura.isNotBlank()) {
                            IconButton(onClick = { altura = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Limpar caixa de texto"
                                )
                            }
                        }
                    }
                )

                Row(
                    Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            CalcIac()
                        },
                        Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Black,
                            Color.White
                        ),
                        enabled = validadeQuadril && validadeAltura //validação no botão
                    ) {
                        Text(
                            text = "Calcular", fontSize = 20.sp, fontFamily = FontFamily.SansSerif
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    Arrangement.Center,
                    Alignment.Bottom
                ) {
                    OutlinedTextField(
                        value = resultado,
                        onValueChange = {resultado = it},
                        readOnly = true,
                        label = { Text(text = "Resultado:")},
                        modifier = Modifier
                            .width(150.dp)
                            .align(CenterVertically)
                    )
                }

            }
        }
        Row (
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            TextButton(
                onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.periodicos.unc.br/index.php/sma/article/view/638"
                                )
                        )
                    )
                          },
                border = null,
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = "Saiba mais",
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp
                )
            }
        }
    }
}




@Preview
@Composable
fun CalcPreb() {
    CalculadoraCliente()
}
