package com.uuranus.home.calendar

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale


@SuppressLint("ConstantLocale")
private val dateFormatYM = SimpleDateFormat("yyyy년 MM월", Locale.getDefault())

@SuppressLint("ConstantLocale")
private val dateFormatYMD = SimpleDateFormat("yyyy년 MM월", Locale.getDefault())

fun getFormattedYMDate(calendar: Calendar): String {
    return dateFormatYM.format(calendar.time)
}

fun getFormattedYMDDate(calendar: Calendar): String {
    return dateFormatYMD.format(calendar.time)
}

fun getNumberOfDaysInMonth(year: Int, month: Int): Int {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1) // Calendar 클래스의 월은 0부터 시작하므로 입력된 월에서 1을 빼야 합니다.
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
}

fun getFirstDayOfMonth(year: Int, month: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month - 1) // Calendar 클래스의 월은 0부터 시작하므로 입력된 월에서 1을 빼야 합니다.
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    return calendar
}

fun getFirstDayOfWeek(calendar: Calendar): Int {
    return (calendar.get(Calendar.DAY_OF_WEEK) - Calendar.MONDAY + 7) % 7
}

fun Calendar.addMonth(month: Int): Calendar {
    val calendar = this.clone() as Calendar
    calendar.add(Calendar.MONTH, month)
    return calendar
}