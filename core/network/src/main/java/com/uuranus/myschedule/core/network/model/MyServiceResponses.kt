package com.uuranus.myschedule.core.network.model


data class CalendarResponse(
    val month: Int,
    val date: Int,
    val works: List<String>,
)