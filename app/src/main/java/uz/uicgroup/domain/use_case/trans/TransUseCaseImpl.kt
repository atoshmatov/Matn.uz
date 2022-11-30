package uz.uicgroup.domain.use_case.trans

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.domain.repository.trans.TranslationRepository
import javax.inject.Inject

class TransUseCaseImpl @Inject constructor(
    private val transRepository: TranslationRepository
) : TransUseCase {
    override fun getLatin(text: String): Flow<Resource<String>> = flow {
        val response = transRepository.translateToLatin(text)
        when (response.code()) {
            200 -> {
                emit(Resource.Loading())
                emit(Resource.Success(response.body().toString()))
            }
            400 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
            500 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
        }
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: ""))
    }

    override fun getCyrille(text: String): Flow<Resource<String>> = flow {
        val response = transRepository.translateToCyrillic(text)
        when (response.code()) {
            200 -> {
                emit(Resource.Loading())
                emit(Resource.Success(response.body().toString()))
            }
            400 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
            500 -> {
                emit(Resource.Error(response.message() ?: ""))
            }
        }
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: ""))
    }
}