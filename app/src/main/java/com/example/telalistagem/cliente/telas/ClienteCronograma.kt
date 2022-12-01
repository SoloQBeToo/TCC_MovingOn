package com.example.telalistagem.cliente.telas

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.actions.NoteIntents
import java.util.*

class ClienteCronograma : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CronogramaCliente()
        }
    }
}



@Composable
fun CronogramaCliente(){

    val context = LocalContext.current


    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).apply {
                        val beginTime: Calendar = Calendar.getInstance().apply {
                            set(2022,12,7)
                        }
                        val endTime = Calendar.getInstance().apply {
                            set(2023, 0, 23, 10, 30)
                        }
                        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
                        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                        putExtra(CalendarContract.Events.TITLE, "Cronograma Moving On")
                        putExtra(CalendarContract.Events.EVENT_LOCATION, "Lugar x")
                    }
                )
            } ,
            Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Min),
            elevation = ButtonDefaults.elevation(3.dp)
        ) {
            Icon(
                Icons.Filled.EventAvailable, contentDescription = "",
                modifier = Modifier.padding(end=15.dp))
            Text(
                text = "Ir pra o cronograma"
            )
        }
    }

}


@Preview
@Composable
fun CronogramaPrev() {
    CronogramaCliente()
}