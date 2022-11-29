package uz.uicgroup.presentation.screens.about.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.uicgroup.presentation.screens.about.viewModel.AppInfoViewModel
import javax.inject.Inject

@HiltViewModel
class AppInfoViewModelImpl @Inject constructor() : AppInfoViewModel, ViewModel() {
    override val backLiveData = MutableLiveData<Unit>()

    override fun backScreen() {
        backLiveData.value = Unit
    }
}