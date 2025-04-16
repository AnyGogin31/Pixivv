package io.anygogin31.pixivv.core.storage.provider

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlin.io.path.Path
import kotlin.io.path.div

internal class JvmDataStoreProvider : DataStoreProvider {
    override fun createStore(name: String): DataStore<Preferences> {
        return createDataStore(
            producePath = {
                val osName = System.getProperty("os.name").lowercase()
                val userHomeDir = System.getProperty("user.home")
                val appDataDir = System.getenv("APPDATA")

                val configDir =
                    when (osName) {
                        "windows" -> Path(appDataDir ?: userHomeDir)
                        else -> Path(userHomeDir) / ".config"
                    }

                (configDir / "pixivv" / name).toString()
            },
        )
    }
}
