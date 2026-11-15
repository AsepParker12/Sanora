package com.music.bitchord.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.music.bitchord.R
import com.music.bitchord.data.settings.AppAccent
import com.music.bitchord.data.settings.AppFont

// Apple Music's signature red. No longer the primary accent, but kept for the
// spots (Replay's rank badge) that want that specific red regardless of theme.
val AccentRed = Color(0xFFFA2D48)

// SONARA's aurora identity: violet into cyan, the launcher gradient's two ends.
val SonaraViolet = Color(0xFF7C3AED)
val SonaraCyan = Color(0xFF06B6D4)

// SONARA's Aurora schemes: violet primary running into cyan, over surfaces
// tinted with the same violet instead of upstream's neutral monochrome.
// Dark keeps a brightened violet primary so it stays readable on the deep
// violet-black ground; light uses the brand violet itself and a darker cyan
// for contrast. (The player still derives its own colours from the artwork;
// these schemes govern everything around it.)
private val DarkColors = darkColorScheme(
    primary = Color(0xFFA78BFA),
    onPrimary = Color(0xFF2E1065),
    primaryContainer = Color(0xFF4C1D95),
    onPrimaryContainer = Color(0xFFEDE9FE),
    secondary = Color(0xFF22D3EE),
    onSecondary = Color(0xFF083344),
    secondaryContainer = Color(0xFF155E75),
    onSecondaryContainer = Color(0xFFCFFAFE),
    tertiary = SonaraCyan,
    onTertiary = Color(0xFF083344),
    tertiaryContainer = Color(0xFF164E63),
    onTertiaryContainer = Color(0xFFCFFAFE),
    background = Color(0xFF0D0A14),
    onBackground = Color(0xFFF5F3FF),
    surface = Color(0xFF171224),
    onSurface = Color(0xFFF5F3FF),
    surfaceVariant = Color(0xFF251D3D),
    onSurfaceVariant = Color(0xFFB8AED6),
    surfaceDim = Color(0xFF0D0A14),
    surfaceBright = Color(0xFF372C52),
    surfaceContainerLowest = Color(0xFF08060E),
    surfaceContainerLow = Color(0xFF120D1E),
    surfaceContainer = Color(0xFF1B1629),
    surfaceContainerHigh = Color(0xFF241C38),
    surfaceContainerHighest = Color(0xFF2C2347),
    outline = Color(0xFF3B2F57),
    outlineVariant = Color(0xFF2A2140),
    inverseSurface = Color(0xFFF5F3FF),
    inverseOnSurface = Color(0xFF1D1830),
    inversePrimary = SonaraViolet,
)

private val LightColors = lightColorScheme(
    primary = SonaraViolet,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFEDE9FE),
    onPrimaryContainer = Color(0xFF4C1D95),
    secondary = Color(0xFF0891B2),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCFFAFE),
    onSecondaryContainer = Color(0xFF155E75),
    tertiary = Color(0xFF0E7490),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFD9F6FC),
    onTertiaryContainer = Color(0xFF164E63),
    background = Color(0xFFF7F5FC),
    onBackground = Color(0xFF1D1830),
    surface = Color.White,
    onSurface = Color(0xFF1D1830),
    surfaceVariant = Color(0xFFEFEAF9),
    onSurfaceVariant = Color(0xFF5B5470),
    surfaceDim = Color(0xFFDCD4EE),
    surfaceBright = Color.White,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFFBFAFE),
    surfaceContainer = Color(0xFFF3EFFB),
    surfaceContainerHigh = Color(0xFFECE5F8),
    surfaceContainerHighest = Color(0xFFE4DBF4),
    outline = Color(0xFFCFC6E4),
    outlineVariant = Color(0xFFE3DCF2),
    inverseSurface = Color(0xFF1D1830),
    inverseOnSurface = Color(0xFFF5F3FF),
    inversePrimary = Color(0xFFA78BFA),
)

// Accent variants of the Aurora schemes: only the accent roles (primary,
// secondary, tertiary, their containers and inversePrimary) are swapped, via
// copy(), so backgrounds and surfaces stay the Aurora-tinted neutrals above
// whatever accent is picked — see AppAccent. AURORA is DarkColors/LightColors
// themselves, unchanged.
private val DarkRose = DarkColors.copy(
    primary = Color(0xFFFDA4AF),
    onPrimary = Color(0xFF4C0519),
    primaryContainer = Color(0xFF881337),
    onPrimaryContainer = Color(0xFFFFE4E6),
    secondary = Color(0xFFF9A8D4),
    onSecondary = Color(0xFF500724),
    secondaryContainer = Color(0xFF831843),
    onSecondaryContainer = Color(0xFFFCE7F3),
    tertiary = Color(0xFFF0ABFC),
    onTertiary = Color(0xFF4A044E),
    tertiaryContainer = Color(0xFF701A75),
    onTertiaryContainer = Color(0xFFFAE8FF),
    inversePrimary = Color(0xFFE11D48),
)

private val LightRose = LightColors.copy(
    primary = Color(0xFFE11D48),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE4E6),
    onPrimaryContainer = Color(0xFF881337),
    secondary = Color(0xFFDB2777),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFCE7F3),
    onSecondaryContainer = Color(0xFF831843),
    tertiary = Color(0xFFC026D3),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFAE8FF),
    onTertiaryContainer = Color(0xFF701A75),
    inversePrimary = Color(0xFFFDA4AF),
)

private val DarkEmerald = DarkColors.copy(
    primary = Color(0xFF6EE7B7),
    onPrimary = Color(0xFF022C22),
    primaryContainer = Color(0xFF064E3B),
    onPrimaryContainer = Color(0xFFD1FAE5),
    secondary = Color(0xFF5EEAD4),
    onSecondary = Color(0xFF042F2E),
    secondaryContainer = Color(0xFF134E4A),
    onSecondaryContainer = Color(0xFFCCFBF1),
    tertiary = Color(0xFFA3E635),
    onTertiary = Color(0xFF1A2E05),
    tertiaryContainer = Color(0xFF3F6212),
    onTertiaryContainer = Color(0xFFECFCCB),
    inversePrimary = Color(0xFF059669),
)

private val LightEmerald = LightColors.copy(
    primary = Color(0xFF059669),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD1FAE5),
    onPrimaryContainer = Color(0xFF064E3B),
    secondary = Color(0xFF0D9488),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCCFBF1),
    onSecondaryContainer = Color(0xFF134E4A),
    tertiary = Color(0xFF65A30D),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFECFCCB),
    onTertiaryContainer = Color(0xFF3F6212),
    inversePrimary = Color(0xFF6EE7B7),
)

private val DarkSunset = DarkColors.copy(
    primary = Color(0xFFFDBA74),
    onPrimary = Color(0xFF431407),
    primaryContainer = Color(0xFF7C2D12),
    onPrimaryContainer = Color(0xFFFFEDD5),
    secondary = Color(0xFFFCD34D),
    onSecondary = Color(0xFF451A03),
    secondaryContainer = Color(0xFF78350F),
    onSecondaryContainer = Color(0xFFFEF3C7),
    tertiary = Color(0xFFFCA5A5),
    onTertiary = Color(0xFF450A0A),
    tertiaryContainer = Color(0xFF7F1D1D),
    onTertiaryContainer = Color(0xFFFEE2E2),
    inversePrimary = Color(0xFFEA580C),
)

private val LightSunset = LightColors.copy(
    primary = Color(0xFFEA580C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFEDD5),
    onPrimaryContainer = Color(0xFF7C2D12),
    secondary = Color(0xFFD97706),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = Color(0xFF78350F),
    tertiary = Color(0xFFDC2626),
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFFEE2E2),
    onTertiaryContainer = Color(0xFF7F1D1D),
    inversePrimary = Color(0xFFFDBA74),
)

/** The scheme in force: [accent]'s family, in its dark or light cut. */
private fun sonaraColorScheme(darkTheme: Boolean, accent: AppAccent) = when (accent) {
    AppAccent.AURORA -> if (darkTheme) DarkColors else LightColors
    AppAccent.ROSE -> if (darkTheme) DarkRose else LightRose
    AppAccent.EMERALD -> if (darkTheme) DarkEmerald else LightEmerald
    AppAccent.SUNSET -> if (darkTheme) DarkSunset else LightSunset
}

// Rounder than the Material defaults upstream shipped with (4/8/12/16/28),
// so cards, sheets and chips read as SONARA's rather than stock.
private val SonaraShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp),
)

/**
 * SF Pro Display, the face Apple Music itself is set in, drawn one weight
 * lighter than it is asked for.
 *
 * Measured against Apple Music's own screens, every weight here came out a
 * cut heavier than the same text there: its titles are Semibold where these
 * asked for Bold, its metadata Medium where these asked for Semibold. The
 * shift is made once, in the family, so it reaches the type scale and every
 * weight set in place alike and keeps the steps between them: Heavy draws
 * Bold, Bold draws Semibold, Semibold draws Medium, Medium draws Regular.
 * Regular stays Regular — no lighter cut is bundled, and Apple's body text is
 * Regular too. A FontWeight in this app therefore names the step it sits at in
 * the scale, not the cut it draws.
 *
 * Compose synthesises nothing, so a weight outside these falls back to the
 * nearest one listed.
 */
val SFProDisplay = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.W400),
    Font(R.font.sf_pro_display_regular, FontWeight.W500),
    Font(R.font.sf_pro_display_medium, FontWeight.W600),
    Font(R.font.sf_pro_display_semibold, FontWeight.W700),
    Font(R.font.sf_pro_display_bold, FontWeight.W800),
)

/*
 * Letter spacing measured off Apple Music's own screens: the spacing the
 * bundled Display cut needs, at the weight it draws, to set a line to the
 * width Apple sets it. Apple sets text under 20pt in SF Pro Text, which is
 * spaced looser than Display, so the small sizes open up — about +0.25 to
 * +0.4 at 12 to 14 — and the spacing closes as the size grows, to just under
 * nothing for semibold titles at 18 to 24. The large titles had nothing to
 * measure against and follow the same trend.
 */
private val BitChordTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 34.sp, letterSpacing = (-0.5).sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 30.sp, letterSpacing = (-0.4).sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.W700, fontSize = 22.sp, letterSpacing = (-0.25).sp),
    titleLarge = TextStyle(fontWeight = FontWeight.W700, fontSize = 20.sp, letterSpacing = (-0.2).sp),
    titleMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 16.sp, letterSpacing = 0.05.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.W400, fontSize = 16.sp, letterSpacing = 0.2.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.W400, fontSize = 14.sp, letterSpacing = 0.3.sp),
    labelMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 12.sp, letterSpacing = 0.25.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.W600, fontSize = 11.sp, letterSpacing = 0.3.sp),
).withFamily(SFProDisplay)

/**
 * SF Pro Display as the app first shipped it, before [SFProDisplay] moved every
 * weight a cut lighter: each weight draws its own cut, Heavy included. Kept for
 * [AppFont.LEGACY].
 */
val SFProDisplayLegacy = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.W400),
    Font(R.font.sf_pro_display_medium, FontWeight.W500),
    Font(R.font.sf_pro_display_semibold, FontWeight.W600),
    Font(R.font.sf_pro_display_bold, FontWeight.W700),
    Font(R.font.sf_pro_display_heavy, FontWeight.W800),
)

// Heavy, tight typography — the original scale [AppFont.LEGACY] restores.
private val LegacyTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 34.sp, letterSpacing = (-0.8).sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 30.sp, letterSpacing = (-0.7).sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.W700, fontSize = 22.sp, letterSpacing = (-0.4).sp),
    titleLarge = TextStyle(fontWeight = FontWeight.W700, fontSize = 20.sp, letterSpacing = (-0.3).sp),
    titleMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 16.sp, letterSpacing = (-0.2).sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.W400, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.W400, fontSize = 14.sp),
    labelMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 12.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.W600, fontSize = 11.sp),
).withFamily(SFProDisplayLegacy)

/** Applies [family] to every style in the scale, so nothing is left on Roboto. */
private fun Typography.withFamily(family: FontFamily) = Typography(
    displayLarge = displayLarge.copy(fontFamily = family),
    displayMedium = displayMedium.copy(fontFamily = family),
    displaySmall = displaySmall.copy(fontFamily = family),
    headlineLarge = headlineLarge.copy(fontFamily = family),
    headlineMedium = headlineMedium.copy(fontFamily = family),
    headlineSmall = headlineSmall.copy(fontFamily = family),
    titleLarge = titleLarge.copy(fontFamily = family),
    titleMedium = titleMedium.copy(fontFamily = family),
    titleSmall = titleSmall.copy(fontFamily = family),
    bodyLarge = bodyLarge.copy(fontFamily = family),
    bodyMedium = bodyMedium.copy(fontFamily = family),
    bodySmall = bodySmall.copy(fontFamily = family),
    labelLarge = labelLarge.copy(fontFamily = family),
    labelMedium = labelMedium.copy(fontFamily = family),
    labelSmall = labelSmall.copy(fontFamily = family),
)

@Composable
fun BitChordTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    font: AppFont = AppFont.DEFAULT,
    accent: AppAccent = AppAccent.AURORA,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = sonaraColorScheme(darkTheme, accent),
        shapes = SonaraShapes,
        typography = when (font) {
            AppFont.DEFAULT -> BitChordTypography
            AppFont.LEGACY -> LegacyTypography
        },
        content = content,
    )
}

/**
 * Draws the status and navigation bar glyphs dark or light.
 *
 * `enableEdgeToEdge()` decides this from the *system* dark-mode setting, which
 * is the wrong input the moment the in-app theme disagrees with it: Light theme
 * on a phone in dark mode left white icons on a white bar, invisible. The bars
 * have to follow the content the app is actually painting. The player supplies
 * its own stable light-icon value and paints contrast behind it; every other
 * surface follows the theme. Hence a parameter rather than reading it here.
 */
@Composable
fun SystemBarIcons(dark: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) return
    val window = findWindow(view) ?: return
    SideEffect {
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = dark
            isAppearanceLightNavigationBars = dark
        }
    }
}

