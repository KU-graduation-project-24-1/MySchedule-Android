package com.uuranus.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uuranus.myschedule.core.designsystem.R

private val pretendardFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

private val pretendardStyle = TextStyle(
    fontFamily = pretendardFamily
)

internal val Typography = MyScheduleTypography(
    bold20 = pretendardStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bold16 = pretendardStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    semiBold20 = pretendardStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    semiBold16 = pretendardStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    regular14 = pretendardStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    semiBold12 = pretendardStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    regular10 = pretendardStyle.copy(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    semiBold8 = pretendardStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 8.sp
    )
)

@Immutable
data class MyScheduleTypography(
    val bold20: TextStyle,
    val bold16: TextStyle,
    val semiBold20: TextStyle,
    val semiBold16: TextStyle,
    val regular14: TextStyle,
    val semiBold12: TextStyle,
    val regular10: TextStyle,
    val semiBold8: TextStyle,
)

val LocalTypography = staticCompositionLocalOf { Typography }
