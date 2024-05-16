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

    val weekDay: String
        get() {
            return if (this::calendar.isInitialized) {
                println("Calendar ${calendar.get(Calendar.DAY_OF_WEEK)}")
                when (calendar.get(Calendar.DAY_OF_WEEK)) {
                    Calendar.SUNDAY -> "일"
                    Calendar.MONDAY -> "월"
                    Calendar.TUESDAY -> "화"
                    Calendar.WEDNESDAY -> "수"
                    Calendar.THURSDAY -> "목"
                    Calendar.FRIDAY -> "금"
                    Calendar.SATURDAY -> "토"
                    else -> ""
                }
            } else {
                ""
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
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DATE, date)

            return DateInfo(year, month, date).apply {
                setCalendar(calendar)
            }
        }

    }

}

