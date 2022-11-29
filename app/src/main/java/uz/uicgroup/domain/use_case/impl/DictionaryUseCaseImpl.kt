package uz.uicgroup.domain.use_case.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.remote.response.DictionaryDto
import uz.uicgroup.data.remote.response.toDicData
import uz.uicgroup.data.remote.response.toWordsData
import uz.uicgroup.domain.model.DictionaryData
import uz.uicgroup.domain.model.WordData
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
    }.catch {
        emit(Resource.Error(it.localizedMessage?:""))
    }

    override fun getWords(id: Int): Flow<Resource<WordData>> = flow {
        val response = dicRep.getWords(id)
        when (response.code()) {
            200 -> {
                emit(Resource.Success(response.body().toWordsData()))
            }
            400 -> {
                emit(Resource.Error(response.message()))
            }
            500 -> {
                emit(Resource.Error(response.message()))
            }
        }
    }.catch {
        emit(Resource.Error(it.localizedMessage?:""))
    }
}