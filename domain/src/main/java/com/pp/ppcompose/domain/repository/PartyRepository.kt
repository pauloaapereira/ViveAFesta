package com.pp.ppcompose.domain.repository

import com.pp.ppcompose.domain.Party
import java.time.LocalDateTime

interface PartyRepository {
    fun getParties(
        name: String? = null,
        municipality: String? = null,
        district: String? = null,
        dates: Pair<LocalDateTime, LocalDateTime>? = null,
    ): List<Party>
}