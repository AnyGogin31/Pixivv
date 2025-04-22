package io.anygogin31.pixivv.core.storage.domain

import io.anygogin31.pixivv.core.storage.KeyValueStorage
import io.anygogin31.pixivv.core.storage.PersistedKeyValueStorage

public class SessionPixivKeyValueStorage(
    storage: (name: String) -> PersistedKeyValueStorage,
) : KeyValueStorage by storage(KEY) {
    private companion object {
        private const val KEY: String = "session.pixiv.preferences_pb"
    }
}
