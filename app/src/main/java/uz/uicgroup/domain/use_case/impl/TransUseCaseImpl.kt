package uz.uicgroup.domain.use_case.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import uz.uicgroup.data.common.ErrorMessage
import uz.uicgroup.data.common.Resource
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.domain.repository.TransRepository
import uz.uicgroup.domain.use_case.TransUseCase
import javax.inject.Inject

class TransUseCaseImpl @Inject constructor(
    private val transRepository: TransRepository
) : TransUseCase {
    override fun getLatin(text: String): Flow<Resource<String>> = flow {
        val response = transRepository.transLatin(text)
        when (response.code()) {
            200 -> {
                emit(Resource.Loading())
                emit(Resource.Success(response.body() ?: ""))
            }
            400 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
            500 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
        }
    }

    override fun getCyrille(text: String): Flow<Resource<String>> = flow {
        val response = transRepository.transCyrillic(text)
        when (response.code()) {
            200 -> {
                emit(Resource.Loading())
                emit(Resource.Success(response.body() ?: ""))
            }
            400 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
            500 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
        }
    }
}