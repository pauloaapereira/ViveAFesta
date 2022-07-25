package com.pp.viveafesta.domain.usecases

import com.pp.viveafesta.domain.Party
import com.pp.viveafesta.domain.repository.PartyRepository
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