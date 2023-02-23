package pro.stuermer.bookii

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import pro.stuermer.bookii.devices.devicesModule
import pro.stuermer.bookii.devices.domain.devicesDomainModule
import timber.log.Timber
class BookiiApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // inject Android context
            androidContext(applicationContext)

            // use modules
            modules(
                listOf(
                    appModule,
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

//        Timber.plant(
//            TimberUploadTree(
//                context = this,
//                url = BuildConfig.API_URL,
//                minLogLevel = Log.VERBOSE
//            )
//        )

        Timber.i("Log setup done.")
    }
}
