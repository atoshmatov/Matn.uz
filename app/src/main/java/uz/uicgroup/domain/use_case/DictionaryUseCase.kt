package uz.uicgroup.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData

interface DictionaryUseCase {
    // TODO: Change name
    fun getDicSearch(search: String): Flow<Resource<List<DictionaryData>>>
    fun getWords(id: Int): Flow<Resource<WordData>>
}