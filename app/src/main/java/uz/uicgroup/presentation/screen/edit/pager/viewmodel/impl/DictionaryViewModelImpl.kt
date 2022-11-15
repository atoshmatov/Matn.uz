package uz.uicgroup.presentation.screen.edit.pager.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.use_case.DictionaryUseCase
import uz.uicgroup.presentation.screen.edit.pager.viewmodel.DictionaryViewModel
import uz.uicgroup.utils.extension.eventValueFlow
import uz.uicgroup.utils.internetConnection.isConnected
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModelImpl
@Inject constructor(
    private val dictionaryUseCase: DictionaryUseCase
) : DictionaryViewModel, ViewModel() {

    override val noConnectionFlow = eventValueFlow<Boolean>()
    override val showLoadingFlow = eventValueFlow<Resource<Boolean>>()
    override val errorFlow = eventValueFlow<String>()
    override val showMassageFlow = eventValueFlow<Resource<String>>()
    override val searchList = eventValueFlow<Resource<List<DictionaryData>>>()
    override val searchQueryFlow = eventValueFlow<String>()


    override fun getSearchWord(search: String) {
        viewModelScope.launch {
            if (!isConnected()) {
                noConnectionFlow.emit(false)
                return@launch
            }
            if (search.isNotEmpty()) {
                dictionaryUseCase.getDicSearch(search)
                    .onStart {
                        showLoadingFlow.emit(Resource.Loading(true))
                    }.onEach { list ->
                        showLoadingFlow.emit(Resource.Loading(false))
                        searchList.emit(Resource.Success(list.data ?: emptyList()))
                    }
                    .catch { errorFlow.emit("Error") }
                    .launchIn(viewModelScope)
            } else {
                searchList.emit(Resource.Empty())
                showLoadingFlow.emit(Resource.Loading(true))
                showMassageFlow.emit(Resource.Error("text maydoni to‘ldirilishi shart."))
            }
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