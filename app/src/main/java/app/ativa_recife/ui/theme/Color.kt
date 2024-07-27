package app.ativa_recife.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)
val Yellow20 = Color(0xfffbe192)
val Orange50 = Color(0xFFe09347)
val Orange = Color(0xFFf3c19d)
val PurpleGrey40 = Color(0xFF625b71)
val OrangeDark = Color(0xFFe8833a)
val Blue50 = Color(0xFF045B96)
val Blue20 = Color(0xFF0098FF)

val ColorScheme.focusedTextFiled
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.Black


val ColorScheme.unfocusedTextField
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.White


val ColorScheme.textFieldContainer
    @Composable
    get() = if (isSystemInDarkTheme())  Color.White else Color.White
