package com.oliverspryn.android.pagestatus.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40
)

@Composable
fun PageStatusTheme(
    isDeviceUsingDarkMode: Boolean = isSystemInDarkTheme(),
    deviceSdkLevel: Int = Build.VERSION.SDK_INT,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val view = LocalView.current
    val systemUiController = rememberSystemUiController()
    val isPreviewingInAndroidStudio = view.isInEditMode

    // Determine which theme type to use
    val themeType = if (deviceSdkLevel >= Build.VERSION_CODES.S) {
        if (isDeviceUsingDarkMode) {
            ThemeType.DARK_DYNAMIC
        } else {
            ThemeType.LIGHT_DYNAMIC
        }
    } else if (isDeviceUsingDarkMode) {
        ThemeType.DARK_BRANDED
    } else {
        ThemeType.LIGHT_BRANDED
    }

    val colorScheme = when (themeType) {
        ThemeType.DARK_BRANDED -> DarkColorScheme
        ThemeType.DARK_DYNAMIC -> dynamicDarkColorScheme(context)
        ThemeType.LIGHT_BRANDED -> LightColorScheme
        ThemeType.LIGHT_DYNAMIC -> dynamicLightColorScheme(context)
    }

    // Set the status bar based on the above decision
    if (!isPreviewingInAndroidStudio) {
        if (themeType.isBrandedTheme()) {
            SideEffect {
                systemUiController.setStatusBarColor(color = Purple40)
            }
        } else if (themeType.isDynamicTheme()) {
            SideEffect {
                val activity = view.context as Activity
                activity.window.statusBarColor = colorScheme.primary.toArgb()

                WindowCompat.getInsetsController(
                    activity.window,
                    view
                ).isAppearanceLightStatusBars = isDeviceUsingDarkMode
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

private fun ThemeType.isBrandedTheme() =
    this == ThemeType.DARK_BRANDED || this == ThemeType.LIGHT_BRANDED

private fun ThemeType.isDynamicTheme() =
    this == ThemeType.DARK_DYNAMIC || this == ThemeType.LIGHT_DYNAMIC

private enum class ThemeType {
    DARK_BRANDED,
    DARK_DYNAMIC,
    LIGHT_BRANDED,
    LIGHT_DYNAMIC
}
