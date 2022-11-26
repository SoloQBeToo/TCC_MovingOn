package com.example.telalistagem.cliente.telas


import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat.apply
import com.example.telalistagem.cliente.MapaViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.*


class ClienteMapa : ComponentActivity() {
    private val viewMapa by viewModels<MapaViewModel>()
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getLocationPermission()
        getDevideLocation()
        setContent {
            MapaCliente()
        }
    }

    private fun getLocationPermission() {
        if(ContextCompat.checkSelfPermission(this.applicationContext, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            viewMapa.permissionGranted(true)
            getDevideLocation()
        }else{
            Toast.makeText(applicationContext,"Permissão não garantida",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getDevideLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try {
            if(viewMapa.locationPermissionGranted.value == true){
                val locationResult = fusedLocationProviderClient.lastLocation

                locationResult.addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        val lastKnownLocation = task.result

                        if(lastKnownLocation != null){
                            viewMapa.currentUserGeoCoord(
                                LatLng(
                                    lastKnownLocation.altitude,
                                    lastKnownLocation.longitude
                                )
                            )
                        }
                    }else{
                        Toast.makeText(applicationContext,"Localização nula",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }catch (e: SecurityException){
            Toast.makeText(applicationContext,e.message,Toast.LENGTH_SHORT).show()
        }
    }
}


@Composable
fun MapaCliente(){



    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val latlng =
            LatLng(/*viewModel.userCurrentLat.value, viewModel.userCurrentLng.value*/-23.522136,
                -46.469323
            )
        val cameraPositionState: CameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latlng, 14f)
        }

        GoogleMap(
            modifier = Modifier
                .matchParentSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                mapType = MapType.TERRAIN,
                isIndoorEnabled = true,
                isBuildingEnabled = true,
                isMyLocationEnabled = true
            ),
            contentPadding = PaddingValues(30.dp),
            onMapClick = {

            },
            onMapLongClick = {

            },


        ){

        }
    }



}



@Composable
fun AddMarker(latLng: LatLng){
    Marker(state = MarkerState(latLng))
    MarkerOptions()
        .position(latLng)
        .draggable(true)
        .title("Local Selecionado")
        .snippet
}


