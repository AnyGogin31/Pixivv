package io.anygogin31.pixivv.core.theme.provider

import androidx.compose.material3.ColorScheme

public interface ThemeProvider {
    public val isDynamicColorSupported: Boolean
    public val dynamicDarkColorScheme: ColorScheme
    public val dynamicLightColorScheme: ColorScheme
}
