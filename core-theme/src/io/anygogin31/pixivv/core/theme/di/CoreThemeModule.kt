package io.anygogin31.pixivv.core.theme.di

import org.koin.core.module.Module
import org.koin.dsl.module

public val CoreThemeModule: Module =
    module {
        includes(PlatformCoreThemeModule)
    }

internal expect val PlatformCoreThemeModule: Module
