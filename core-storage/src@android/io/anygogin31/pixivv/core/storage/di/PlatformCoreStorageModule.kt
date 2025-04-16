package io.anygogin31.pixivv.core.storage.di

import io.anygogin31.pixivv.core.storage.provider.AndroidDataStoreProvider
import io.anygogin31.pixivv.core.storage.provider.DataStoreProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val PlatformCoreStorageModule: Module =
    module {
        factoryOf(::AndroidDataStoreProvider) { bind<DataStoreProvider>() }
    }
