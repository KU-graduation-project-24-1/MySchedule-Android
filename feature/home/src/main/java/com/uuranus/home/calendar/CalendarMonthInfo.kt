package com.uuranus.home.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar


@Composable
fun rememberMonthInfo(currentDate: Calendar): MonthInfo {
    val year = currentDate.get(Calendar.YEAR)
    val month = currentDate.get(Calendar.MONTH) + 1
    return remember(currentDate) {
        MonthInfo(
            numberOfDays = getNumberOfDaysInMonth(year, month),
            firstDayOfWeek = getFirstDayOfWeek(getFirstDayOfMonth(year, month))
        )
    }
}

data class MonthInfo(val numberOfDays: Int, val firstDayOfWeek: Int)

