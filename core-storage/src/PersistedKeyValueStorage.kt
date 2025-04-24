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

package io.anygogin31.pixivv.core.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import io.anygogin31.pixivv.core.storage.provider.DataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

public class PersistedKeyValueStorage(
    private val dataStoreProvider: DataStoreProvider,
    private val name: String,
) : KeyValueStorage {
    private val store: DataStore<Preferences> by lazy {
        dataStoreProvider.createStore(name)
    }

    override suspend fun <T> get(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): T {
        return store.data
            .map { it[key] ?: defaultValue }
            .first()
    }

    override suspend fun <T> getOrNull(key: Preferences.Key<T>): T? {
        return store.data
            .map { it[key] }
            .first()
    }

    override suspend fun <T> getOrThrow(key: Preferences.Key<T>): T {
        return store.data
            .map { it[key]!! }
            .first()
    }

    override suspend fun <T> set(
        key: Preferences.Key<T>,
        value: T,
    ): Result<Unit> {
        return runCatching {
            store.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    override suspend fun <T> remove(key: Preferences.Key<T>): Result<Unit> {
        return runCatching {
            store.edit { preferences ->
                preferences.remove(key)
            }
        }
    }

    override fun <T> observe(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return store.data
            .map { it[key] ?: defaultValue }
    }
}
