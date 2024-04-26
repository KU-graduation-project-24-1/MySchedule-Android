package com.uuranus.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

internal val White = Color(0xFFFFFFFF)
internal val Black = Color(0xFF040706)
internal val DARK = Color(0xFF040706)
internal val LightGray = Color(0xFFECECEC)
internal val Gray = Color(0xFFECECEC)
internal val DarkGray = Color(0xFF969696)

internal val PrimaryGreen = Color(0xFFC1DAB9)
internal val AccentGreen = Color(0xFFAACB9F)

internal val ErrorRed = Color(0xFFD52727)

//calendar colors
internal val Red = Color(0xFFF3A8A8)
internal val Blue = Color(0xFFA8C6E3)
internal val Orange = Color(0xFFFBCFAE)
internal val Yellow = Color(0xFFF9EB9E)
internal val Purple = Color(0xFFC8C7F6)
internal val Green = Color(0xFF9FE9B4)


internal val Purple80 = Color(0xFFD0BCFF)
internal val PurpleGrey80 = Color(0xFFCCC2DC)
internal val Pink80 = Color(0xFFEFB8C8)

internal val Purple40 = Color(0xFF6650a4)
internal val PurpleGrey40 = Color(0xFF625b71)
internal val Pink40 = Color(0xFF7D5260)


@Immutable
public data class MyScheduleColors(
    val primary: Color,
    val background: Color,
    val backgroundLight: Color,
    val backgroundDark: Color,
    val textColor: Color,
    val textGrayColor: Color,
    val absoluteWhite: Color,
    val absoluteBlack: Color,
    val white: Color,
    val black: Color,
    val lightGray: Color,
    val gray: Color,
    val darkGray: Color,
    val calendarRed: Color,
    val calendarOrange: Color,
    val calendarYellow: Color,
    val calendarGreen: Color,
    val calendarBlue: Color,
    val calendarPurple: Color,
) {

    public companion object {
        @Composable
        public fun defaultDarkColors(): MyScheduleColors = MyScheduleColors(
            primary = PrimaryGreen,
            background = White,
            backgroundLight = White,
            backgroundDark = DARK,
            textColor = Black,
            textGrayColor = DarkGray,
            absoluteWhite = White,
            absoluteBlack = Black,
            white = White,
            black = Black,
            lightGray = LightGray,
            gray = Gray,
            darkGray = DarkGray,
            calendarRed = Red,
            calendarOrange = Orange,
            calendarYellow = Yellow,
            calendarGreen = Green,
            calendarBlue = Blue,
            calendarPurple = Purple
        )

        /**
         * Provides the default colors for the light mode of the app.
         *
         * @return A [PokedexColors] instance holding our color palette.
         */
        @Composable
        public fun defaultLightColors(): MyScheduleColors = MyScheduleColors(
            primary = PrimaryGreen,
            background = White,
            backgroundLight = White,
            backgroundDark = DARK,
            textColor = Black,
            textGrayColor = DarkGray,
            absoluteWhite = White,
            absoluteBlack = Black,
            white = White,
            black = Black,
            lightGray = LightGray,
            gray = Gray,
            darkGray = DarkGray,
            calendarRed = Red,
            calendarOrange = Orange,
            calendarYellow = Yellow,
            calendarGreen = Green,
            calendarBlue = Blue,
            calendarPurple = Purple
        )
    }
}