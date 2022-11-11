package uz.uicgroup.domain.use_case.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.CorrectData
import uz.uicgroup.domain.model.SuggestionsData
import uz.uicgroup.data.remote.response.toCorrect
import uz.uicgroup.data.remote.response.toSuggestion
import uz.uicgroup.domain.repository.SpellingRepository
import uz.uicgroup.domain.use_case.SpellingUseCase
import javax.inject.Inject

class SpellingUseCaseImpl @Inject constructor(
    private val spellingRepository: SpellingRepository
) : SpellingUseCase {
    override fun getCorrect(correct: List<String>): Flow<Resource<CorrectData>> = flow {
        val response = spellingRepository.getCorrectTextList(correct)
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
    }
}

/*
private fun CorrectDto?.toCorrect(): CorrectDto {
    return CorrectDto(
        errors = this?.errors ?: false,
        data = this?.data ?: emptyList()
    )
}*/
