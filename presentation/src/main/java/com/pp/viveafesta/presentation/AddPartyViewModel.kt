package com.pp.viveafesta.presentation

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pp.viveafesta.domain.usecases.AddParty
import com.pp.viveafesta.presentation.models.ViewParty
import com.pp.viveafesta.presentation.models.ViewPartyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPartyViewModel @Inject constructor(
    private val addParty: AddParty,
    private val viewPartyMapper: ViewPartyMapper
): ViewModel() {

    var name by mutableStateOf("")
    var district by mutableStateOf("")
    var municipality by mutableStateOf("")
    var dateRange by mutableStateOf("")
    var hasParking by mutableStateOf(false)
    var hasFood by mutableStateOf(false)
    var hasAmusement by mutableStateOf(false)
    var isPaid by mutableStateOf(false)
    var poster by mutableStateOf<Bitmap?>(null)
    var location by mutableStateOf<Location?>(null)

    fun addParty() {
        val viewParty = ViewParty(
            name = name,
            district = district,
            municipality = municipality,
            dateRange = dateRange,
            poster = poster,
            location = location,
            hasFood = hasFood,
            isPaid = isPaid,
            hasParking = hasParking,
            hasAmusement = hasAmusement,
        )

        viewModelScope.launch {
            addParty(viewPartyMapper.mapTo(viewParty))
        }
    }
    
}