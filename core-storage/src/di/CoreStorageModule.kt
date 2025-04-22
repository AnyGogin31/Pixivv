package io.anygogin31.pixivv.core.storage.di

import io.anygogin31.pixivv.core.storage.PersistedKeyValueStorage
import io.anygogin31.pixivv.core.storage.domain.PixivvKeyValueStorage
import io.anygogin31.pixivv.core.storage.domain.SessionPixivKeyValueStorage
import io.anygogin31.pixivv.core.storage.provider.DataStoreProvider
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

public val CoreStorageModule: Module =
    module {
        includes(PlatformCoreStorageModule)

        factory { (name: String) ->
            PersistedKeyValueStorage(
                dataStoreProvider = get<DataStoreProvider>(),
                name = name,
            )
        }

        single {
            PixivvKeyValueStorage { name: String ->
                get<PersistedKeyValueStorage> { parametersOf(name) }
            }
        }

        single {
            SessionPixivKeyValueStorage { name: String ->
                get<PersistedKeyValueStorage> { parametersOf(name) }
            }
        }
    }

internal expect val PlatformCoreStorageModule: Module
