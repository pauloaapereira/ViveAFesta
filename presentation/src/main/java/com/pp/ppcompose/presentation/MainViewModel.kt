package com.pp.ppcompose.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pp.ppcompose.domain.usecases.GetParties
import com.pp.ppcompose.presentation.models.Filters
import com.pp.ppcompose.presentation.models.ViewParty
import com.pp.ppcompose.presentation.models.ViewPartyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getParties: GetParties,
    private val viewPartyMapper: ViewPartyMapper
) : ViewModel() {

    val parties = mutableStateListOf<ViewParty>()
    private var filters by mutableStateOf(Filters())

    fun fetchParties() {
        viewModelScope.launch {
            parties.clear()
            parties.addAll(
                getParties(
                    name = filters.name,
                    placeName = filters.municipality,
                    district = filters.district,
                    dates = filters.dateRange
                ).map { viewPartyMapper.mapFrom(it) }
            )
        }
    }

    fun applyFilters(
        name: String? = null,
        district: String? = null,
        municipality: String? = null,
        dateRange: Pair<LocalDateTime, LocalDateTime>? = null
    ) {
        this.filters = this.filters.copy(
            name = name,
            district = district,
            municipality = municipality,
            dateRange = dateRange
        )
    }

}