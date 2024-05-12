package com.uuranus.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle

fun String.toAnnotateString(changeText: String, changedColor: Color): AnnotatedString {
    val text = AnnotatedString.Builder(this)
    text.withStyle(style = SpanStyle(color = changedColor)) {
        append(changeText)
    }

    return text.toAnnotatedString()
}

