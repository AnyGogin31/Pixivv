package io.anygogin31.pixivv

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

public class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
        }
    }
}
