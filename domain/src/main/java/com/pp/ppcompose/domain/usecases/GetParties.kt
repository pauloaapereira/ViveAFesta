package com.pp.ppcompose.domain.usecases

import com.pp.ppcompose.domain.Party
import com.pp.ppcompose.domain.repository.PartyRepository
import java.time.LocalDateTime
import javax.inject.Inject

class GetParties @Inject constructor(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(
        name: String? = null,
        placeName: String? = null,
        district: String? = null,
        dates: Pair<LocalDateTime, LocalDateTime>? = null,
    ): List<Party> = partyRepository.getParties(
        name, placeName, district, dates
    )
}