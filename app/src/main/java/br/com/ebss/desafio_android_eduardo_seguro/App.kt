package br.com.ebss.desafio_android_eduardo_seguro

import android.app.Application
import br.com.ebss.desafio_android_eduardo_seguro.utils.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(mainModule)
        }
    }
}