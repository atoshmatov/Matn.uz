package uz.uicgroup.presentation.screen.edit.pager.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.model.WordData
import uz.uicgroup.domain.use_case.DictionaryUseCase
import uz.uicgroup.domain.use_case.HistoryUseCase
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.DictionaryViewModel
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModelImpl
@Inject constructor(
    private val dictionaryUseCase: DictionaryUseCase, private val history: HistoryUseCase
) : DictionaryViewModel, ViewModel() {

    override val noConnectionFlow = MutableSharedFlow<Boolean>()
    override val showLoadingFlow = eventValueFlow<Boolean>()
    override val errorFlow = eventValueFlow<String>()
    override val showMassageFlow = eventValueFlow<Resource<String>>()
    override val searchList = eventValueFlow<Resource<List<DictionaryData>>>()
    override val searchQueryFlow = eventValueFlow<String>()
    override val wordsFlow = MutableSharedFlow<Resource<WordData>>()
    override val historyList = eventValueFlow<Resource<List<WordData>>>()
    override var click: Boolean = false
    override val showImageEmptyFlow = eventValueFlow<Boolean>()

    override fun getSearchWord(search: String) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (search.isNotEmpty()) {
                showLoadingFlow.emit(true)
                dictionaryUseCase.getDicSearch(search).collect { list ->
                    noConnectionFlow.emit(true)
                    showImageEmptyFlow.emit(list.data?.isEmpty() == true)
                    if (list.data?.isNotEmpty() == true) {
                        searchList.emit(Resource.Success(list.data ?: emptyList()))
                    } else {
                        searchList.emit(Resource.Empty())
                    }
                }
            } else {
                searchList.emit(Resource.Empty())
            }
        }
    }

    override fun getWords(id: Int) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            dictionaryUseCase.getWords(id).collect { words ->
                noConnectionFlow.emit(true)
                wordsFlow.emit(Resource.Success(words.data!!))
            }
        }
    }

    override fun getHistory() {
        click = true
        viewModelScope.launch {
            history.getHistoryList().collect { history ->
                showImageEmptyFlow.emit(history.data!!.isEmpty())
                showLoadingFlow.emit(history.data.isEmpty())
                searchList.emit(Resource.Success(history.data ?: emptyList()))
            }
        }
    }

    override fun getById(id: Int) {
        viewModelScope.launch {
            history.getByIdWord(id).collect { word ->
                wordsFlow.emit(Resource.Success(word.data!!))
            }
        }
    }

    override fun addWordHistory(word: WordData) {
        viewModelScope.launch(Dispatchers.IO) {
            history.addHistory(word).catch {}.collect {}
        }
    }

    override fun delete() {
        viewModelScope.launch {
            history.deleteTable().catch {}.collect {}
        }
    }


    override fun onSearch(query: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
    }

    override fun close() {
        viewModelScope.launch(Dispatchers.IO) {
            searchQueryFlow.emit("")
        }
    }
}