package com.taskflow.presentation.ui.theme
import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val Primary      = Color(0xFF222222)
val BgScreen     = Color(0xFFFFFFFF)
val BgCard       = Color(0xF5F5F5F5)
val TextTitle    = Color(0xFF111827)
val TextBody     = Color(0xFF374151)
val TextMuted    = Color(0xFF999999)
val TextUnselected    = Color(0xFF888888)
val BorderCard   = Color(0xFFEEEEEE)
val RestoreBtnBackground   = Color(0xFFE8F5E9)
val RestoreBtnTextColor   = Color(0xFF2E7D32)
val DeleteBtnBackground   = Color(0xFFFFEBEE)
val DeleteeBtnTextColor   = Color(0xFFD32F2F)
val EditTextBorderColor   = Color(0xFFDDDDDD)


private val LightColors = lightColorScheme(
    primary = Primary,
    background = BgScreen,
    surface = BgCard,
    surfaceVariant = BgCard,
    onPrimary = Color.White,
    onBackground = TextTitle,
    onSurface = TextTitle
)

@Composable
fun TaskFlowTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = BgScreen.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    MaterialTheme(colorScheme = LightColors, content = content)
}
