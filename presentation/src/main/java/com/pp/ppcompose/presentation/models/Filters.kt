package com.pp.ppcompose.presentation.models

import java.time.LocalDateTime

data class Filters(
    val name: String? = null,
    val district: String? = null,
    val municipality: String? = null,
    val dateRange: Pair<LocalDateTime, LocalDateTime>? = null,
)
