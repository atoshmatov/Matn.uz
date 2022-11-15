package uz.uicgroup.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.remote.response.DictionaryDto
import uz.uicgroup.domain.model.DictionaryData

interface DictionaryUseCase {
    fun getDicSearch(search: String): Flow<Resource<List<DictionaryData>>>
}