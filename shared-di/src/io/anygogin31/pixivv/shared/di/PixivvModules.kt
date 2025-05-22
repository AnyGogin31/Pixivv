/*
 * MIT License
 *
 * Copyright (c) 2025 AnyGogin31
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.anygogin31.pixivv.shared.di

import io.anygogin31.pixivv.core.remote.auth.di.CoreRemoteAuthModule
import io.anygogin31.pixivv.core.storage.di.CoreStorageModule
import io.anygogin31.pixivv.core.theme.di.CoreThemeModule
import io.anygogin31.pixivv.data.di.DataModule
import io.anygogin31.pixivv.data.remote.di.DataRemoteModule
import io.anygogin31.pixivv.domain.di.DomainModule
import io.anygogin31.pixivv.screen.walkthrough.di.ScreenWalkthroughModule
import org.koin.core.module.Module

public val PixivvModules: List<Module> =
    listOf(
        CoreRemoteAuthModule,
        CoreStorageModule,
        CoreThemeModule,
        DataModule,
        DataRemoteModule,
        DomainModule,
        ScreenWalkthroughModule,
    )
