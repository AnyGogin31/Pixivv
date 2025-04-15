package io.anygogin31.pixivv.core.storage

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

public interface KeyValueStorage {
    public suspend fun <T> get(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): T

    public suspend fun <T> getOrNull(key: Preferences.Key<T>): T?

    public suspend fun <T> getOrThrow(key: Preferences.Key<T>): T

    public suspend fun <T> set(
        key: Preferences.Key<T>,
        value: T,
    ): Result<Unit>

    public suspend fun <T> remove(key: Preferences.Key<T>): Result<Unit>

    public fun <T> observe(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T>
}
