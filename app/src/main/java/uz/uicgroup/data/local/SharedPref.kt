package uz.uicgroup.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) {
    private val pref = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)

    var stateBtn: Boolean
        get() = pref.getBoolean("state", false)
        set(value) = pref.edit().putBoolean("state", value).apply()

    var theme: Boolean
        get() = pref.getBoolean("theme", false)
        set(value) = pref.edit().putBoolean("theme", value).apply()
}