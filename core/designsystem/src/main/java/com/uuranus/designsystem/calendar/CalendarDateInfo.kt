package com.uuranus.designsystem.calendar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.Calendar

@Composable
fun rememberMonthInfo(currentDate: DateInfo): MonthInfo {
    return remember(currentDate) {
        MonthInfo(
            numberOfDays = getNumberOfDaysInMonth(currentDate.year, currentDate.month),
            firstDayOfWeek = getFirstDayOfWeek(
                getFirstDayOfMonth(
                    currentDate.year,
                    currentDate.month
                )
            )
        )
    }
}

data class MonthInfo(val numberOfDays: Int, val firstDayOfWeek: Int)


data class DateInfo(val year: Int, val month: Int, val date: Int) {
    private lateinit var calendar: Calendar

    fun getWeekDay(): String {
        return when (calendar.get(Calendar.DAY_OF_WEEK)) {
            1 -> "일"
            2 -> "월"
            3 -> "화"
            4 -> "수"
            5 -> "목"
            6 -> "금"
            7 -> "토"
            else -> ""
        }
    }

    fun addMonth(monthNum: Int): DateInfo {
        val newCalendar = calendar.clone() as Calendar
        newCalendar.add(Calendar.MONTH, monthNum)
        return create(newCalendar).apply {
            setCalendar(newCalendar)
        }
    }

    fun setDate(date: Int): DateInfo {
        val newCalendar = calendar.clone() as Calendar
        newCalendar.set(Calendar.DATE, date)
        return DateInfo(
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH) + 1,
            newCalendar.get(Calendar.DATE)
        ).apply { setCalendar(newCalendar) }
    }

    private fun setCalendar(calendar: Calendar) {
        this.calendar = calendar
    }

    fun isPossibleAdd(): Boolean {

        val today = Calendar.getInstance()
        val nextMonth = today.addMonth(2)
        nextMonth.set(Calendar.DATE, 1)

        val thisMonth = today.addMonth(1)
        thisMonth.set(Calendar.DATE, 1)

        return if (calendar.get(Calendar.DATE) < 8) {
            calendar < thisMonth
        } else {
            calendar < nextMonth
        }
    }

    companion object {
        fun create(calendar: Calendar): DateInfo {
            return DateInfo(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DATE)
            ).apply { setCalendar(calendar) }
        }

        fun create(year: Int, month: Int, date: Int): DateInfo {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month - 1)
            calendar.set(Calendar.DATE, date)

            return DateInfo(year, month, date).apply {
                setCalendar(calendar)
            }
        }

    }

}

