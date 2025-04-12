package io.anygogin31.pixivv.shared.entry

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.KoinApplication

@Composable
public fun EntryView(modifier: Modifier = Modifier) {
    KoinApplication(
        application = {},
    ) {
        PixivvEntryView(modifier = modifier)
    }
}
