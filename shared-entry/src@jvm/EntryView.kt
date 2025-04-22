package io.anygogin31.pixivv.shared.entry

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Window
import io.anygogin31.pixivv.shared.di.PixivvModules
import org.koin.compose.KoinApplication

@Composable
public fun EntryView(
    onCloseRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "Untitled",
    icon: Painter? = null,
) {
    Window(
        onCloseRequest = onCloseRequest,
        title = title,
        icon = icon,
    ) {
        KoinApplication(
            application = {
                modules(PixivvModules)
            },
        ) {
            PixivvEntryView(modifier = modifier)
        }
    }
}
