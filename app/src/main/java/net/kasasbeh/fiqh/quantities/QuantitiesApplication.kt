package net.kasasbeh.fiqh.quantities

import android.app.Application
import net.kasasbeh.fiqh.quantities.repository.DataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class QuantitiesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single { DataStoreRepository(androidContext()) }
        }

        startKoin {
            androidLogger()
            androidContext(this@QuantitiesApplication)
            modules(appModule)
        }
    }
}