package com.pp.ppcompose.presentation.models

import android.graphics.Bitmap
import android.location.Location

data class ViewParty(
    val name: String,
    val district: String,
    val municipality: String,
    val dateRange: String,
    val poster: Bitmap?,
    val location: Location?,
    val hasFood: Boolean,
    val isPaid: Boolean,
    val hasParking: Boolean,
    val hasAmusement: Boolean,
)