package io.anygogin31.pixivv.core.storage.provider

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal class AndroidDataStoreProvider(private val context: Context) : DataStoreProvider {
    override fun createStore(name: String): DataStore<Preferences> {
        return createDataStore(
            producePath = {
                context.filesDir.resolve(name).absolutePath
            },
        )
    }
}
