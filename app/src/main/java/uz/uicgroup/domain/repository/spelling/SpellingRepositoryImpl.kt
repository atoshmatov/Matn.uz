package uz.uicgroup.domain.repository.spelling

import retrofit2.Response
import uz.uicgroup.data.mapper.CorrectDto
import uz.uicgroup.data.mapper.SuggestionsDto
import uz.uicgroup.data.remote.api.SpellingApi
import uz.uicgroup.data.remote.models.TextList
import javax.inject.Inject

class SpellingRepositoryImpl @Inject constructor(
    private val apiSpelling: SpellingApi
) : SpellingRepository {
    override suspend fun getCorrectTextS(text: List<String>): Response<CorrectDto> =
        apiSpelling.correctText(TextList(text))

    override suspend fun getSuggestionsText(text: List<String>): Response<SuggestionsDto> =
        apiSpelling.suggestionsText(TextList(text))
}