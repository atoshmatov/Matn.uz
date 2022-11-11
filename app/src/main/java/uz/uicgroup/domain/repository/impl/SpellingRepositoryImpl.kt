package uz.uicgroup.domain.repository.impl

import retrofit2.Response
import uz.uicgroup.data.remote.api.SpellingApi
import uz.uicgroup.data.remote.request.TextList
import uz.uicgroup.data.remote.response.CorrectDto
import uz.uicgroup.data.remote.response.SuggestionsDto
import uz.uicgroup.domain.repository.SpellingRepository
import javax.inject.Inject

class SpellingRepositoryImpl @Inject constructor(
    private val apiSpelling: SpellingApi
) : SpellingRepository {
    override suspend fun getCorrectTextList(text: List<String>): Response<CorrectDto> =
        apiSpelling.correctText(
            TextList(text)
        )

    override suspend fun getSuggestionsText(text: List<String>): Response<SuggestionsDto> =
        apiSpelling.suggestionsText(
            TextList(text)
        )

}