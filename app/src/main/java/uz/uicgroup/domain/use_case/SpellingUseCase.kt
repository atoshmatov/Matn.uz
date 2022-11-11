package uz.uicgroup.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.CorrectData
import uz.uicgroup.domain.model.SuggestionsData

interface SpellingUseCase {
    fun getCorrect(correct: List<String>): Flow<Resource<CorrectData>>
    fun getSuggestions(correct: List<String>): Flow<Resource<SuggestionsData>>
}