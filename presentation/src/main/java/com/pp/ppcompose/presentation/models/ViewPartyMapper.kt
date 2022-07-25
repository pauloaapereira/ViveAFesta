package com.pp.ppcompose.presentation.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.util.Base64
import com.pp.ppcompose.domain.Party
import com.pp.ppcompose.domain.Mapper
import java.time.LocalDateTime
import javax.inject.Inject


class ViewPartyMapper @Inject constructor() : Mapper<Party, ViewParty> {
    override fun mapFrom(input: Party): ViewParty =
        ViewParty(
            name = input.name,
            district = input.district,
            municipality = input.municipality,
            dateRange = "${input.startDate} to ${input.endDate}",
            poster = decodeBase64(input.poster),
            location = if (input.latitude != null && input.longitude != null) {
                Location("").apply {
                    latitude = input.latitude!!
                    longitude = input.longitude!!
                }
            } else null,
            hasFood = input.hasFood,
            isPaid = input.isPaid,
            hasParking = input.hasParking,
            hasAmusement = input.hasAmusement
        )

    override fun mapTo(input: ViewParty): Party =
        Party(
            name = input.name,
            district = input.district,
            municipality = input.municipality,
            startDate = LocalDateTime.parse(input.dateRange.split(" to ").getOrNull(0)),
            endDate = LocalDateTime.parse(input.dateRange.split(" to ").getOrNull(1)),
            poster = input.poster?.toString(),
            latitude = input.location?.latitude,
            longitude = input.location?.longitude,
            hasFood = input.hasFood,
            isPaid = input.isPaid,
            hasParking = input.hasParking,
            hasAmusement = input.hasAmusement
        )

    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}