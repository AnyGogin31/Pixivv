package io.anygogin31.pixivv.core.storage.di

import org.koin.core.module.Module
import org.koin.dsl.module

public val CoreStorageModule: Module =
    module {
        includes(PlatformCoreStorageModule)
    }

internal expect val PlatformCoreStorageModule: Module
