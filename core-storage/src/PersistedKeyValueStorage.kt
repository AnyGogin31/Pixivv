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
