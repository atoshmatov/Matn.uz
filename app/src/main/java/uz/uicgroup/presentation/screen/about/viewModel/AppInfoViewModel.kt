package uz.uicgroup.presentation.screen.about.viewModel

import androidx.lifecycle.LiveData

interface AppInfoViewModel {
    val backLiveData: LiveData<Unit>

    fun backScreen()
}