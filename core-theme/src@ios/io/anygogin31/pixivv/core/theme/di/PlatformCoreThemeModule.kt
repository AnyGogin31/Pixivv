package io.anygogin31.pixivv.core.theme.di

import io.anygogin31.pixivv.core.theme.provider.IosThemeProvider
import io.anygogin31.pixivv.core.theme.provider.ThemeProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val PlatformCoreThemeModule: Module =
    module {
        factoryOf(::IosThemeProvider) { bind<ThemeProvider>() }
    }
