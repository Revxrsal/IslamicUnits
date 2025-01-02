package net.kasasbeh.islamicunits

import android.app.Application
import androidx.room.Room
import net.kasasbeh.islamicunits.data.room.FavoritesDatabase
import net.kasasbeh.islamicunits.repository.DataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class IslamicUnitsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val appModule = module {
            single { DataStoreRepository(androidContext()) }
            single {
                Room.databaseBuilder(
                    this@IslamicUnitsApplication,
                    FavoritesDatabase::class.java,
                    "favoritesData"
                ).build()
            }
            viewModel { FavoritesViewModel(get()) }
        }

        startKoin {
            androidLogger()
            androidContext(this@IslamicUnitsApplication)
            modules(appModule)
        }
    }
}