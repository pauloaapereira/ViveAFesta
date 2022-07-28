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
package com.pp.viveafesta.presentation.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.util.Base64
import com.pp.viveafesta.domain.Mapper
import com.pp.viveafesta.domain.Party
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import javax.inject.Inject

class ViewPartyMapper @Inject constructor() : Mapper<Party, ViewParty> {
    override fun mapFrom(input: Party): ViewParty =
        ViewParty(
            name = input.name,
            district = input.district,
            municipality = input.municipality,
            dateRange = "${input.dateRange.first} to ${input.dateRange.second}",
            poster = input.poster?.let { decodeBase64(input.poster) },
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
            dateRange = with(input.dateRange.split(" to ")) {
                Pair(
                    LocalDate.parse(getOrNull(0)),
                    LocalDate.parse(getOrNull(1))
                )
            },
            poster = input.poster?.let { encodeTobase64(it) },
            latitude = input.location?.latitude,
            longitude = input.location?.longitude,
            hasFood = input.hasFood,
            isPaid = input.isPaid,
            hasParking = input.hasParking,
            hasAmusement = input.hasAmusement
        )

    fun encodeTobase64(image: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 90, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun decodeBase64(input: String?): Bitmap? {
        val decodedByte: ByteArray = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
    }
}
