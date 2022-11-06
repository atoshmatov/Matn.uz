package uz.uicgroup.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.uicgroup.presentation.viewModel.MainViewModel
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.eventFlow
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor() : ViewModel(), MainViewModel {
    override val showLoadingFlow = eventValueFlow<String>()
    override val notConnection = eventValueFlow<Boolean>()
    override val openNextScreenFLow = eventFlow()
    override val openNoErrorScreenFLow = eventFlow()
    override val messageFlow = eventValueFlow<String>()

    init {
        viewModelScope.launch {
            delay(2000)
            if (!Open.notInternet)
                openNoErrorScreenFLow.emit(Unit)
        }
    }


    override fun openEditorScreen() {
        viewModelScope.launch {
            if (!isConnected()) {
                notConnection.emit(false)
                return@launch
            }
            if (Open.notInternet)
                openNextScreenFLow.emit(Unit)
        }
    }
}