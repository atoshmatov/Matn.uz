package uz.uicgroup.presentation.screens.about.viewModel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface AboutViewModel {
    val backScreenLiveData: LiveData<Unit>
    val nightModeLiveData: LiveData<Boolean>
    val sharedPrefValue: Flow<Boolean>

    fun backScreen()
    fun setTheme(nightMode: Boolean)
    fun getSharedPrefThemeValue()
}