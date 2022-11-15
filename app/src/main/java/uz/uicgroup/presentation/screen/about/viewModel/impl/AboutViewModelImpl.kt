package uz.uicgroup.presentation.screen.about.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.presentation.screen.about.viewModel.AboutViewModel
import uz.uicgroup.utils.Theme.goInDarkMode
import uz.uicgroup.utils.Theme.goInLightMode
import javax.inject.Inject

@HiltViewModel
class AboutViewModelImpl @Inject constructor(
    private val sharedPref: SharedPref
) : ViewModel(), AboutViewModel {
    override val backScreenLiveData = MutableLiveData<Unit>()
    override val nightModeLiveData = MutableLiveData<Boolean>()

    override fun backScreen() {
        backScreenLiveData.value = Unit
    }

    override fun setTheme(nightMode: Boolean) {
        if (nightMode) {
            goInLightMode()
            sharedPref.theme = true
            nightModeLiveData.value = sharedPref.theme
        } else {
            goInDarkMode()
            sharedPref.theme = false
            nightModeLiveData.value =  sharedPref.theme
        }
    }
}