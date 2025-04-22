package io.anygogin31.pixivv.shared.di

import io.anygogin31.pixivv.core.remote.auth.di.CoreRemoteAuthModule
import io.anygogin31.pixivv.core.storage.di.CoreStorageModule
import io.anygogin31.pixivv.core.theme.di.CoreThemeModule
import org.koin.core.module.Module

public val PixivvModules: List<Module> =
    listOf(
        CoreRemoteAuthModule,
        CoreStorageModule,
        CoreThemeModule,
    )
