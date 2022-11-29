package uz.uicgroup.domain.use_case

import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.WordData
import  kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.local.WordEntity
import uz.uicgroup.data.remote.response.WordDto
import uz.uicgroup.domain.model.DictionaryData

interface HistoryUseCase {
    fun getHistoryList(): Flow<Resource<List<DictionaryData>>>
    fun getByIdWord(id: Int): Flow<Resource<WordData>>
    fun addHistory(latin: WordData): Flow<Resource<Unit>>
    fun deleteTable(): Flow<Resource<Unit>>
}