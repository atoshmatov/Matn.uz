package uz.uicgroup.presentation.screen.edit.pager.viewmodel

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.DictionaryData

interface DictionaryViewModel {

    val noConnectionFlow: Flow<Boolean>
    val showLoadingFlow: Flow<Resource<Boolean>>
    val errorFlow: Flow<String>
    val showMassageFlow: Flow<Resource<String>>
    val searchList: Flow<Resource<List<DictionaryData>>>
    val searchQueryFlow: Flow<String>

    fun getSearchWord(search: String)
    fun onSearch(query: String)
    fun close()
}