package com.uuranus.myschedule.core.network.model.mapper

import com.uuranus.myschedule.core.network.model.CalendarResponse

internal fun CalendarResponse.toData(): CalendarResponse = CalendarResponse(0, 0, emptyList())