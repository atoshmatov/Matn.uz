package uz.uicgroup.utils

import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Singleton

@Singleton
object Theme {
    fun goInDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun goInLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}