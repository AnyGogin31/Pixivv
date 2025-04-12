import androidx.compose.ui.window.ComposeUIViewController
import io.anygogin31.pixivv.shared.entry.EntryView
import platform.UIKit.UIViewController

public fun ViewController(): UIViewController {
    return ComposeUIViewController {
        EntryView()
    }
}
