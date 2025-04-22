package io.anygogin31.pixivv.core.remote.auth.di

import io.anygogin31.pixivv.core.remote.auth.providers.PixivAuthProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public val CoreRemoteAuthModule: Module =
    module {
        singleOf(::PixivAuthProvider)
    }
