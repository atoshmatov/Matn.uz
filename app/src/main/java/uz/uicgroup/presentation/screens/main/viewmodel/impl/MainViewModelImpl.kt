package uz.uicgroup.presentation.screens.main.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.presentation.screens.main.viewmodel.MainViewModel
import uz.uicgroup.utils.Open
import uz.uicgroup.utils.extension.eventFlow
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val sharedPref: SharedPref
) : ViewModel(),
    MainViewModel {
    override val showLoadingFlow = eventValueFlow<String>()
    override val notConnection = eventValueFlow<Boolean>()
    override val openNextScreenFLow = eventFlow()
    override val openNoErrorScreenFLow = eventFlow()
    override val messageFlow = eventValueFlow<String>()

    init {
        viewModelScope.launch {
            if (sharedPref.stateBtn && isConnected()) {
                delay(2000)
                openNextScreenFLow.emit(Open.openScreen.value!!)
            }
        }
    }

    override fun openEditorScreen() {
        sharedPref.stateBtn = true
        viewModelScope.launch {
            if (!isConnected()) {
                notConnection.emit(false)
                return@launch
            }
            openNextScreenFLow.emit(Open.openScreen.value!!)
        }
    }
}