package uz.uicgroup.domain.repository

import retrofit2.Response
import uz.uicgroup.data.remote.response.CorrectDto
import uz.uicgroup.data.remote.response.SuggestionsDto

interface SpellingRepository {
    suspend fun getCorrectTextList(text: List<String>): Response<CorrectDto>
    suspend fun getSuggestionsText(text: List<String>): Response<SuggestionsDto>
}