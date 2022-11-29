package uz.uicgroup.presentation.screens.about.viewModel

import androidx.lifecycle.LiveData

interface AppInfoViewModel {
    val backLiveData: LiveData<Unit>

    fun backScreen()
}