package uz.uicgroup.domain.use_case.dictionary

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.domain.models.DictionaryData
import uz.uicgroup.domain.models.WordData
import uz.uicgroup.utils.common.Resource

interface DictionaryUseCase {
    fun getDicSearch(search: String): Flow<Resource<List<DictionaryData>>>
    fun getWords(id: Int): Flow<Resource<WordData>>
}