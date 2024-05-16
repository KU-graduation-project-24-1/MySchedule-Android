package com.uuranus.designsystem.calendar

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getLanguageYMDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월", dateInfo.year, dateInfo.month)
}

fun getLanguageYMDDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월 %d일", dateInfo.year, dateInfo.month, dateInfo.date)
}

fun getLanguageYMWDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월 %s요일", dateInfo.year, dateInfo.month, dateInfo.weekDay)
}

fun getDashYMDDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월 %d일", dateInfo.year, dateInfo.month, dateInfo.date)
}

fun String.dashToDateInfo(): DateInfo {
    val dates = split("-").map { it.toInt() }
    return DateInfo.create(
        dates[0],
        dates[1],
        dates[2]
    )
}

fun String.lanYDDToDateInfo(): DateInfo {
    val year = this.substringBefore("년").toInt()
    val month = this.substringAfter("년 ").substringBefore("월").toInt()
    val day = this.substringAfter("월 ").substringBefore("일").toInt()

    return DateInfo.create(
        year,
        month,
        day
    )
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

fun getSystemWeeks(): List<String> =
    SimpleDateFormat("EEEEE", Locale.getDefault()).run {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val week = mutableListOf<String>()
        repeat(7) {
            week.add(format(cal.time))
            cal.add(Calendar.DAY_OF_WEEK, 1)
        }
        week
    }