package uz.uicgroup.presentation.viewModel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.domain.use_case.TransUseCase
import uz.uicgroup.presentation.viewModel.EditorViewModel
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class EditorViewModelImpl @Inject constructor(
    private val transUseCase: TransUseCase
) : ViewModel(), EditorViewModel {
    override val _text = eventValueFlow<String>()
    override val showLoadingFlow = eventValueFlow<Boolean>()
    override val noConnectionFlow = eventValueFlow<Boolean>()
    override val showMassageFlow = eventValueFlow<String>()
    override val errorFlow = eventValueFlow<String>()
    override val buttonLive = MutableLiveData<Boolean>()

    override fun getLatin(text: LatinRequest) {
        viewModelScope.launch {
            transUseCase.getLatin(text).onEach {
                if (!isConnected()) {
                    noConnectionFlow.emit(false)
                    return@onEach
                }
                it.onSuccess { latin ->
                    _text.emit(latin)
                }.onFailure { error ->
                    errorFlow.emit(error.toString())
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun getCyrillic(text: LatinRequest) {
        viewModelScope.launch {
            transUseCase.getCyrille(text).onEach {
                if (!isConnected()) {
                    noConnectionFlow.emit(false)
                    return@onEach
                }
                it.onSuccess { cyrillic ->
                    _text.emit(cyrillic)
                }.onFailure { error ->
                    errorFlow.emit(error.toString())
                }
            }.launchIn(viewModelScope)
        }
    }
}