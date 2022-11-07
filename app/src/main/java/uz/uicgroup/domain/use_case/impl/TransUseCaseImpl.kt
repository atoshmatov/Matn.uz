package uz.uicgroup.domain.use_case.impl

import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.uicgroup.data.common.ErrorMessage
import uz.uicgroup.data.remote.request.LatinRequest
import uz.uicgroup.domain.repository.TransRepository
import uz.uicgroup.domain.use_case.TransUseCase
import javax.inject.Inject

class TransUseCaseImpl @Inject constructor(
    private val transRepository: TransRepository,
    private val gson: Gson
) : TransUseCase {
    override fun getLatin(text: LatinRequest): Flow<Result<String>> = flow {
        val response = transRepository.transLatin(text)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            }
        } else {
            /*var error = ErrorMessage(400, "text maydoni to‘ldirilishi shart")
            response.errorBody()?.string()?.let {
                error = gson.fromJson(it, ErrorMessage::class.java)
            }
            emit(Result.failure(Exception(error.message)))*/
        }
    }

    override fun getCyrille(text: LatinRequest): Flow<Result<String>> = flow {
        val response = transRepository.transCyrillic(text)
        if (response.isSuccessful) {
            response.body()?.let {
                emit(Result.success(it))
            }
        } else {
            /*var error = ErrorMessage(400, "text maydoni to‘ldirilishi shart")
            response.errorBody()?.string()?.let {
                error = gson.fromJson(it, ErrorMessage::class.java)
            }
            emit(Result.failure(Exception(error.message)))*/
        }
    }
}