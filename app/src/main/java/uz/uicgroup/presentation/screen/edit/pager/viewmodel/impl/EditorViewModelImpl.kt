package uz.uicgroup.presentation.screen.edit.pager.viewmodel.impl

import android.text.Editable
import android.text.Selection
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.local.SharedPref
import uz.uicgroup.domain.model.CorrectData
import uz.uicgroup.domain.model.SuggestionsData
import uz.uicgroup.domain.use_case.SpellingUseCase
import uz.uicgroup.domain.use_case.TransUseCase
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.EditorViewModel
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class EditorViewModelImpl @Inject constructor(
    private val transUseCase: TransUseCase,
    private val spellingUseCase: SpellingUseCase,
    private val sharedPref: SharedPref
) : ViewModel(), EditorViewModel {
    override val words = eventValueFlow<Resource<String>>()
    override val showLoadingFlow = eventValueFlow<Resource<Boolean>>()
    override val showCorrectMessageFlow = eventValueFlow<Resource<String>>()
    override val checkLoading = eventValueFlow<Resource<Boolean>>()
    override val corrects = eventValueFlow<Resource<CorrectData>>()
    override val suggestions = eventValueFlow<Resource<SuggestionsData>>()
    override val noConnectionFlow = eventValueFlow<Boolean>()
    override val showMassageFlow = eventValueFlow<Resource<String>>()
    override val errorFlow = eventValueFlow<Resource<String>>()
    override val buttonLive = MutableLiveData<Boolean>()

    override fun getLatin(text: String) {
        viewModelScope.launch {
            if (text.isNotEmpty()) {
                transUseCase.getLatin(text).onStart {
                    showLoadingFlow.emit(Resource.Loading(true))
                }.onEach { texts ->
                    if (!isConnected()) {
                        noConnectionFlow.emit(false)
                        return@onEach
                    }
                    showLoadingFlow.emit(Resource.Loading(false))
                    words.emit(Resource.Success(texts.data.toString()))
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getCyrillic(text: String) {
        viewModelScope.launch {
            if (text.isNotEmpty()) {
                transUseCase.getCyrille(text).onStart {
                    showLoadingFlow.emit(Resource.Loading(true))
                }.onEach {
                    if (!isConnected()) {
                        noConnectionFlow.emit(false)
                        return@onEach
                    }
                    showLoadingFlow.emit(Resource.Loading(false))
                    words.emit(Resource.Success(it.data.toString()))
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getCorrect(correctList: List<String>) {
        viewModelScope.launch {
            if (correctList[0].isNotEmpty()) {
                spellingUseCase.getCorrect(correctList).onStart {
                    checkLoading.emit(Resource.Loading(true))
                }.onEach {
                    if (!isConnected()) {
                        noConnectionFlow.emit(false)
                        return@onEach
                    }
                    checkLoading.emit(Resource.Loading(false))
                    corrects.emit(Resource.Success(it.data!!))
                }.launchIn(viewModelScope)
            } else {
                showCorrectMessageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }

    override fun getSuggestions(suggestionsList: List<String>) {
        viewModelScope.launch {
            if (suggestionsList.isNotEmpty()) {
                spellingUseCase.getSuggestions(suggestionsList).onStart {
                    checkLoading.emit(Resource.Loading(true))
                }.onEach {
                    if (!isConnected()) {
                        noConnectionFlow.emit(false)
                        return@onEach
                    }
                    checkLoading.emit(Resource.Loading(false))
                    suggestions.emit(Resource.Success(it.data!!))
                }.catch {
                }.launchIn(viewModelScope)
            } else {
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
        }
    }


    override fun savePosition(box: EditText) {
        val position: Int = box.length()
        val etext: Editable? = box.text
        Selection.setSelection(etext, position)
    }
}