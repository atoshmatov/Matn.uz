package uz.uicgroup.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.domain.models.CorrectData
import uz.uicgroup.domain.models.SuggestionsData

interface SpellingUseCase {
    fun getCorrect(correct: List<String>): Flow<Resource<CorrectData>>
    fun getSuggestions(correct: List<String>): Flow<Resource<SuggestionsData>>
}