package io.anygogin31.pixivv.core.theme.provider

import androidx.compose.material3.ColorScheme

internal class JvmThemeProvider : ThemeProvider {
    override val isDynamicColorSupported: Boolean
        get() = false

    override val dynamicDarkColorScheme: ColorScheme
        get() = throw UnsupportedOperationException("Dynamic Theme not supported on JVM")

    override val dynamicLightColorScheme: ColorScheme
        get() = throw UnsupportedOperationException("Dynamic Theme not supported on JVM")
}
