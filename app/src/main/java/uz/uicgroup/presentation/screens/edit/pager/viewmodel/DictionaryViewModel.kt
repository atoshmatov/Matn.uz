package uz.uicgroup.presentation.screens.edit.pager.viewmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData

interface DictionaryViewModel {

    val noConnectionFlow: Flow<Boolean>
    val showLoadingFlow: Flow<Boolean>
    val errorFlow: Flow<String>
    val showMassageFlow: Flow<Resource<String>>
    val searchList: Flow<Resource<List<DictionaryData>>>
    val searchQueryFlow: Flow<String>
    val wordsFlow: SharedFlow<Resource<WordData>>
    val historyList: Flow<Resource<List<WordData>>>
    var historyAndApiClickListener: Boolean
    val showImageEmptyFlow: Flow<Boolean>


    fun getSearchWord(search: String)
    fun getWords(id: Int)
    fun getHistory()
    fun getById(id: Int)
    fun addWordHistory(word: WordData)
    fun delete()
    fun onSearch(query: String)
    fun close()
}