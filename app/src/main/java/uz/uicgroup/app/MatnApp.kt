package uz.uicgroup.app

import android.app.Application
import com.mocklets.pluto.Pluto
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.uicgroup.BuildConfig

@HiltAndroidApp
class MatnApp : Application() {
    companion object {
        lateinit var instance: MatnApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Pluto.initialize(this)
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}