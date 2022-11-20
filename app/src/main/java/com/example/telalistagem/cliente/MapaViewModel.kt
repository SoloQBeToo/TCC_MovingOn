package com.example.telalistagem.cliente

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng


class MapaViewModel : ViewModel() {
    private var _isNewLocalSelected = MutableLiveData(false)
    var isNewLocalSelected : MutableLiveData<Boolean> = _isNewLocalSelected

    private var _selectedLat = mutableStateOf(0.0)
    var selectLat : MutableState<Double> = _selectedLat

    private var _selectedLng = mutableStateOf(0.0)
    var selectedLng : MutableState<Double> = _selectedLng

    private var _userCurrentLng = mutableStateOf(0.0)
    var userCurrentLng : MutableState<Double> = _userCurrentLng

    private var _userCurrentLat = mutableStateOf(0.0)
    var userCurrentLat : MutableState<Double> = _userCurrentLat

    val pickUp = LatLng(userCurrentLat.value, userCurrentLng.value)

    private var _locationPermissionGranted = MutableLiveData(false)
    var locationPermissionGranted : LiveData<Boolean> = _locationPermissionGranted

    fun currentUserGeoCoord(latLng: LatLng){
        _userCurrentLat.value = latLng.latitude
        _userCurrentLng.value = latLng.longitude
    }

    fun updateSelectedLocation(status: Boolean){
        _isNewLocalSelected.value = status
    }
    fun permissionGranted(setGranted: Boolean){
        _locationPermissionGranted.value = setGranted
    }

}