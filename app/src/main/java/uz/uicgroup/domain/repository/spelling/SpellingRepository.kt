package uz.uicgroup.domain.repository.spelling

import retrofit2.Response
import uz.uicgroup.data.mapper.CorrectDto
import uz.uicgroup.data.mapper.SuggestionsDto

interface SpellingRepository {
    // TODO: Change names
    suspend fun getCorrectTextS(text: List<String>): Response<CorrectDto>
    suspend fun getSuggestionsText(text: List<String>): Response<SuggestionsDto>
}