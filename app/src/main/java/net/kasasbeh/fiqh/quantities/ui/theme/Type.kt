package net.kasasbeh.fiqh.quantities.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import net.kasasbeh.fiqh.quantities.R

val ReadexPro = FontFamily(
    Font(R.font.readexpro_bold, FontWeight.Bold),
    Font(R.font.readexpro_semibold, FontWeight.SemiBold),
    Font(R.font.readexpro_medium, FontWeight.Medium),
    Font(R.font.readexpro_regular, FontWeight.Normal),
    Font(R.font.readexpro_light, FontWeight.Light),
    Font(R.font.readexpro_extralight, FontWeight.ExtraLight),
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = ReadexPro),
    displayMedium = baseline.displayMedium.copy(fontFamily = ReadexPro),
    displaySmall = baseline.displaySmall.copy(fontFamily = ReadexPro),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = ReadexPro),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = ReadexPro),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = ReadexPro),
    titleLarge = baseline.titleLarge.copy(fontFamily = ReadexPro),
    titleMedium = baseline.titleMedium.copy(fontFamily = ReadexPro),
    titleSmall = baseline.titleSmall.copy(fontFamily = ReadexPro),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = ReadexPro),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = ReadexPro),
    bodySmall = baseline.bodySmall.copy(fontFamily = ReadexPro),
    labelLarge = baseline.labelLarge.copy(fontFamily = ReadexPro),
    labelMedium = baseline.labelMedium.copy(fontFamily = ReadexPro),
    labelSmall = baseline.labelSmall.copy(fontFamily = ReadexPro),
)

