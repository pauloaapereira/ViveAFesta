/*
 * Copyright 2022 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pp.viveafesta.presentation

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pp.viveafesta.domain.usecases.AddParty
import com.pp.viveafesta.domain.usecases.GetParties
import com.pp.viveafesta.presentation.models.Filters
import com.pp.viveafesta.presentation.models.ViewParty
import com.pp.viveafesta.presentation.models.ViewPartyMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getParties: GetParties,
    private val addParty: AddParty,
    private val viewPartyMapper: ViewPartyMapper
) : ViewModel() {

    private var partiesJob: Job? = null

    val parties = mutableStateListOf<ViewParty>()
    var filters by mutableStateOf(Filters())
        private set

    fun fetchParties() {
        partiesJob?.cancel()
        partiesJob = viewModelScope.launch {
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

    fun addParty(
        name: String,
        district: String,
        municipality: String,
        dateRange: String,
        poster: Bitmap?,
        location: Location?,
        hasFood: Boolean,
        isPaid: Boolean,
        hasParking: Boolean,
        hasAmusement: Boolean
    ) {
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
            parties.add(viewParty)
        }
    }

    fun applyFilters(
        name: String? = null,
        district: String? = null,
        municipality: String? = null,
        dateRange: Pair<LocalDate, LocalDate>? = null
    ) {
        this.filters = this.filters.copy(
            name = name,
            district = district,
            municipality = municipality,
            dateRange = dateRange
        )
    }
}
