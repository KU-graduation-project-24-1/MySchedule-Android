package com.uuranus.home.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.Calendar



@Composable
fun rememberMonthInfo(year: Int, month: Int): MonthInfo {
    return remember(year, month) {
        val numberOfDaysInMonth = getNumberOfDaysInMonth(year, month)
        val firstDayOfMonth = getFirstDayOfMonth(year, month)
        val firstDayOfWeek = getFirstDayOfWeek(firstDayOfMonth)
        MonthInfo(numberOfDaysInMonth, firstDayOfWeek)
    }
}

data class MonthInfo(val numberOfDays: Int, val firstDayOfWeek: Int)

