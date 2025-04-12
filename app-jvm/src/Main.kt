package io.anygogin31.pixivv

import androidx.compose.ui.window.application
import io.anygogin31.pixivv.shared.entry.EntryView

public fun main() {
    application {
        EntryView(
            onCloseRequest = ::exitApplication,
        )
    }
}
