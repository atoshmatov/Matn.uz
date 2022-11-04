package uz.uicgroup.presentation.viewModel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.uicgroup.presentation.viewModel.SplashViewModel
import uz.uicgroup.utils.extension.eventFlow
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : ViewModel(), SplashViewModel {
    override val showLoadingFlow = eventValueFlow<String>()
    override val notConnection = eventValueFlow<Boolean>()
    override val openNextScreenFLow = eventFlow()
    override val messageFlow = eventValueFlow<String>()

    init {
        viewModelScope.launch {
            delay(2000)
            if (!isConnected()) {
                notConnection.emit(false)
                return@launch
            }
            openNextScreenFLow.emit(Unit)
        }
    }
}