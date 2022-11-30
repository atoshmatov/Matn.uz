package uz.uicgroup.domain.use_case.history


import  kotlinx.coroutines.flow.Flow
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData
import uz.uicgroup.utils.common.Resource

interface HistoryUseCase {
    fun getHistoryList(): Flow<Resource<List<DictionaryData>>>
    fun getByIdWord(id: Int): Flow<Resource<WordData>>
    fun addHistory(latin: WordData): Flow<Resource<Unit>>
    fun deleteTable(): Flow<Resource<Unit>>
}