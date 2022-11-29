package uz.uicgroup.presentation.screens.about.viewModel

import androidx.lifecycle.LiveData

interface AboutViewModel {
    val backScreenLiveData: LiveData<Unit>
    val nightModeLiveData: LiveData<Boolean>

    fun backScreen()
    fun setTheme(nightMode: Boolean)
}