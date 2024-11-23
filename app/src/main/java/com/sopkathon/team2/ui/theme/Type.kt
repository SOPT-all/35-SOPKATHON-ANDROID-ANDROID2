package com.sopkathon.team2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.and.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

@Immutable
data class GAMJATypography(
    // Wavve PADO TTF
    val headRegular26: TextStyle,
    val headRegular20: TextStyle,
    val headRegular16: TextStyle,
    // Title
    val titleBold24: TextStyle,
    val titleBold20: TextStyle,
    val titleBold18: TextStyle,
    // Body
    val bodyMedium16: TextStyle,
    val bodyMedium14: TextStyle,
    val bodyMedium12: TextStyle,
    // Caption
    val captionRegular8: TextStyle,
)

val defaultGAMJATypography =
    GAMJATypography(
        // Wavve PADO TTF
        headRegular26 = TextStyle(
            fontSize = 26.sp,
            fontFamily = FontFamily(Font(R.font.wavve_pado_regular)),
            fontWeight = FontWeight.W400,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        headRegular20 = TextStyle(
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.wavve_pado_regular)),
            fontWeight = FontWeight.W400,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        headRegular16 = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.wavve_pado_regular)),
            fontWeight = FontWeight.W400,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        // Title
        titleBold24 =
        TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        titleBold20 =
        TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 1.55.em,
            letterSpacing = 0.em
        ),
        titleBold18 =
        TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        // Body
        bodyMedium16 =
        TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        bodyMedium14 =
        TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        bodyMedium12 =
        TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
        // Caption
        captionRegular8 =
        TextStyle(
            fontSize = 8.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 1.55.em,
            letterSpacing = (-0.02).em
        ),
    )

val LocalGAMJATypography = staticCompositionLocalOf { defaultGAMJATypography }
