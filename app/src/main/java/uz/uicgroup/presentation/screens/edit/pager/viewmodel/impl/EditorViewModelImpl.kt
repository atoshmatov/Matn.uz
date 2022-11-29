package uz.uicgroup.presentation.screens.edit.pager.viewmodel.impl

import android.text.Editable
import android.text.Selection
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.domain.models.CorrectData
import uz.uicgroup.domain.models.SuggestionsData
import uz.uicgroup.domain.use_case.SpellingUseCase
import uz.uicgroup.domain.use_case.TransUseCase
import uz.uicgroup.presentation.screens.edit.pager.viewmodel.EditorViewModel
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class EditorViewModelImpl @Inject constructor(
    private val transUseCase: TransUseCase,
    private val spellingUseCase: SpellingUseCase,
) : ViewModel(), EditorViewModel {
    override val words = eventValueFlow<Resource<String>>()
    override val showLoadingFlow = eventValueFlow<Resource<Boolean>>()
    override val showCorrectMessageFlow = eventValueFlow<Resource<String>>()
    override val checkLoading = eventValueFlow<Resource<Boolean>>()
    override val corrects = eventValueFlow<Resource<CorrectData>>()
    override val suggestions = eventValueFlow<Resource<SuggestionsData>>()
    override val noConnectionFlow = MutableSharedFlow<Boolean>()
    override val showMassageFlow = eventValueFlow<Resource<String>>()
    override val errorFlow = eventValueFlow<Resource<String>>()
    override val buttonLive = MutableLiveData<Boolean>()

    override fun getLatin(text: String) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (text.isNotEmpty()) {
                transUseCase.getLatin(text).onStart {
                    showLoadingFlow.emit(Resource.Loading(true))
                }.onEach { texts ->
                    noConnectionFlow.emit(true)
                    showLoadingFlow.emit(Resource.Loading(false))
                    words.emit(Resource.Success(texts.data.toString()))
                }.catch {
                    errorFlow.emit(Resource.Error(it.localizedMessage ?: ""))
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getCyrillic(text: String) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (text.isNotEmpty()) {
                transUseCase.getCyrille(text).onStart {
                    showLoadingFlow.emit(Resource.Loading(true))
                }.onEach {
                    noConnectionFlow.emit(true)
                    showLoadingFlow.emit(Resource.Loading(false))
                    words.emit(Resource.Success(it.data.toString()))
                }.catch {
                    errorFlow.emit(Resource.Error(it.localizedMessage ?: ""))
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getCorrect(correctList: List<String>) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (correctList[0].isNotEmpty()) {
                spellingUseCase.getCorrect(correctList).onStart {
                    checkLoading.emit(Resource.Loading(true))
                }.onEach {
                    noConnectionFlow.emit(true)
                    checkLoading.emit(Resource.Loading(false))
                    corrects.emit(Resource.Success(it.data!!))
                }.catch {
                    errorFlow.emit(Resource.Error(it.localizedMessage ?: ""))
                }.launchIn(viewModelScope)
            } else {
                showCorrectMessageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getSuggestions(suggestionsList: List<String>) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (suggestionsList.isNotEmpty()) {
                spellingUseCase.getSuggestions(suggestionsList).onStart {
                    checkLoading.emit(Resource.Loading(true))
                }.onEach {
                    noConnectionFlow.emit(true)
                    checkLoading.emit(Resource.Loading(false))
                    suggestions.emit(Resource.Success(it.data!!))
                }.catch {
                    errorFlow.emit(Resource.Error(it.localizedMessage ?: ""))
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }


    // TODO: move to Fragment
    override fun savePosition(box: EditText) {
        val position: Int = box.length()
        val etext: Editable? = box.text
        Selection.setSelection(etext, position)
    }
}