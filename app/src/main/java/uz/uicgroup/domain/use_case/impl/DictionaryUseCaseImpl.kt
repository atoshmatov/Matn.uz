package uz.uicgroup.domain.use_case.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.remote.response.DictionaryDto
import uz.uicgroup.data.remote.response.toDicData
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.repository.DictionaryRepository
import uz.uicgroup.domain.use_case.DictionaryUseCase
import javax.inject.Inject

class DictionaryUseCaseImpl @Inject constructor(
    private val dicRep: DictionaryRepository
) : DictionaryUseCase {
    override fun getDicSearch(search: String): Flow<Resource<List<DictionaryData>>> = flow {
        val response = dicRep.getSearch(search)
        when (response.code()) {
            200 -> {
                emit(Resource.Success(response.body()?.map { it.toDicData() } ?: emptyList()))
            }
            400 -> {
                emit(Resource.Error(response.message()))
            }
            500 -> {
                emit(Resource.Error(response.message()))
            }
        }
    }
}