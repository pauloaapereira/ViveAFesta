package com.pp.viveafesta.domain.repository

import com.pp.viveafesta.domain.Party
import java.time.LocalDateTime

interface PartyRepository {
    fun getParties(
        name: String? = null,
        municipality: String? = null,
        district: String? = null,
        dates: Pair<LocalDateTime, LocalDateTime>? = null,
    ): List<Party>
}