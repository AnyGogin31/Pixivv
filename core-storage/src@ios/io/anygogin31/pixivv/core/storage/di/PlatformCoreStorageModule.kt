package io.anygogin31.pixivv.core.storage.di

import io.anygogin31.pixivv.core.storage.provider.DataStoreProvider
import io.anygogin31.pixivv.core.storage.provider.IosDataStoreProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal actual val PlatformCoreStorageModule: Module =
    module {
        factoryOf(::IosDataStoreProvider) { bind<DataStoreProvider>() }
    }
