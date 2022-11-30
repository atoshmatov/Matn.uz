package uz.uicgroup.domain.use_case.spelling

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import uz.uicgroup.data.mapper.toCorrect
import uz.uicgroup.data.mapper.toSuggestion
import uz.uicgroup.domain.models.CorrectData
import uz.uicgroup.domain.models.SuggestionsData
import uz.uicgroup.domain.repository.spelling.SpellingRepository
import uz.uicgroup.utils.common.Resource
import javax.inject.Inject

class SpellingUseCaseImpl @Inject constructor(
    private val spellingRepository: SpellingRepository
) : SpellingUseCase {
    override fun getCorrect(correct: List<String>): Flow<Resource<CorrectData>> = flow {
        val response = spellingRepository.getCorrectTextS(correct)
        when (response.code()) {
            200 -> {
                emit(Resource.Success(response.body().toCorrect()))
            }
            400 -> {
                emit(Resource.Error(response.message()))
            }
            500 -> {
                emit(Resource.Error(response.message()))
            }
        }

    }.catch {
        emit(Resource.Error(it.localizedMessage ?: ""))
    }

    override fun getSuggestions(correct: List<String>): Flow<Resource<SuggestionsData>> = flow {
        val response = spellingRepository.getSuggestionsText(correct)
        when (response.code()) {
            200 -> {
                emit(Resource.Success(response.body().toSuggestion()))
            }
            400 -> {
                emit(Resource.Error(response.message()))
            }
            500 -> {
                emit(Resource.Error(response.message()))
            }
        }
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: ""))
    }
}