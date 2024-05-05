package com.uuranus.home.calendar

import java.util.Calendar

fun getFormattedYMDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월", dateInfo.year, dateInfo.month)
}

fun getFormattedYMDDate(dateInfo: DateInfo): String {
    return String.format("%4d년 %d월 %d일", dateInfo.year, dateInfo.month, dateInfo.date)
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