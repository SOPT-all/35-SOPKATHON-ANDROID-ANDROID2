package com.sopkathon.team2.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


object GAMJATheme {
    val colors: GAMJAColors
        @Composable
        @ReadOnlyComposable
        get() = LocalGAMJAColors.current

    val typography: GAMJATypography
        @Composable
        @ReadOnlyComposable
        get() = LocalGAMJATypography.current
}

@Composable
fun ProvideGAMJAColorsAndTypography(
    colors: GAMJAColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalGAMJAColors provides colors,
        content = content,
    )
}

@Composable
fun ANDSOPTSOPKATHONTEAM2Theme(
    backgroundColor: Color = defaultGAMJAColors.gray11,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current

    ProvideGAMJAColorsAndTypography(colors = defaultGAMJAColors) {
        if (!view.isInEditMode) {
            SideEffect {
                (view.context as Activity).window.run {
                    statusBarColor = backgroundColor.toArgb()
                    WindowCompat.getInsetsController(this, view).isAppearanceLightStatusBars = true
                }
            }
        }
        MaterialTheme(
            typography = Typography,
            content = content,
        )
    }
}
