package uz.uicgroup.presentation.screens.about.appinfo.viewmodel

import androidx.lifecycle.LiveData

interface AppInfoViewModel {
    val backLiveData: LiveData<Unit>

    fun backScreen()
}