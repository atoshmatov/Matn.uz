package uz.uicgroup.presentation.screen.about.viewModel

import androidx.lifecycle.LiveData

interface AboutViewModel {
    val backScreenLiveData:LiveData<Unit>
    val nightModeLiveData:LiveData<Boolean>

    fun backScreen()
    fun setTheme(nightMode: Boolean)
}