package com.sopkathon.team2.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


// Gray Scale
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val Gray01 = Color(0xFFFBFBFB)
val Gray02 = Color(0xFFF5F5F5)
val Gray03 = Color(0xFFEEEEEE)
val Gray04 = Color(0xFFE4E4E4)
val Gray05 = Color(0xFFD2D2D2)
val Gray06 = Color(0xFFB7B7B7)
val Gray07 = Color(0xFF949597)
val Gray08 = Color(0xFF57595D)
val Gray09 = Color(0xFF3C3C40)
val Gray10 = Color(0xFF252528)
val Gray11 = Color(0xFF121413)

val Main = Color(0xFFFFCC64)


// Alert
val Red = Color(0xFFFF5757)

@Immutable
data class GAMJAColors(
    // Gray Scale
    val white: Color,
    val black: Color,
    val gray01: Color,
    val gray02: Color,
    val gray03: Color,
    val gray04: Color,
    val gray05: Color,
    val gray06: Color,
    val gray07: Color,
    val gray08: Color,
    val gray09: Color,
    val gray10: Color,
    val gray11: Color,

    val main: Color,

    // Alert
    val red: Color,
)

val defaultGAMJAColors =
    GAMJAColors(
        // Gray Scale
        white = White,
        black = Black,
        gray01 = Gray01,
        gray02 = Gray02,
        gray03 = Gray03,
        gray04 = Gray04,
        gray05 = Gray05,
        gray06 = Gray06,
        gray07 = Gray07,
        gray08 = Gray08,
        gray09 = Gray09,
        gray10 = Gray10,
        gray11 = Gray11,

        main = Main,
        // Alert
        red = Red
    )

val LocalGAMJAColors =
    staticCompositionLocalOf {
        defaultGAMJAColors
    }
