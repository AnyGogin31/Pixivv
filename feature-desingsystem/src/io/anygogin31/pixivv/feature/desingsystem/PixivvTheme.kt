package io.anygogin31.pixivv.feature.desingsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import io.anygogin31.pixivv.core.theme.provider.ThemeProvider
import org.koin.compose.koinInject

private val PixivvDarkColorScheme: ColorScheme = darkColorScheme()
private val PixivvLightColorScheme: ColorScheme = lightColorScheme()

private val PixivvTypography: Typography = Typography()

@Composable
public fun PixivvTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    isDynamicColor: Boolean = true,
    themeProvider: ThemeProvider = koinInject(),
    content: @Composable () -> Unit,
) {
    val dynamicColor: Boolean = isDynamicColor && themeProvider.isDynamicColorSupported
    val colorScheme: ColorScheme =
        when {
            dynamicColor && isDarkTheme -> themeProvider.dynamicDarkColorScheme
            dynamicColor && !isDarkTheme -> themeProvider.dynamicLightColorScheme
            isDarkTheme -> PixivvDarkColorScheme
            else -> PixivvLightColorScheme
        }

    val typography: Typography = PixivvTypography

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
    )
}
