package uz.uicgroup.presentation.screen.edit.pager.viewmodel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.model.WordData

interface DictionaryViewModel {

    val noConnectionFlow: Flow<Boolean>
    val showLoadingFlow: Flow<Boolean>
    val errorFlow: Flow<String>
    val showMassageFlow: Flow<Resource<String>>
    val searchList: Flow<Resource<List<DictionaryData>>>
    val searchQueryFlow: Flow<String>
    val wordsFlow: SharedFlow<Resource<WordData>>
    val historyList: Flow<Resource<List<WordData>>>
    var click: Boolean
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